package br.upe.ppsw.jabberpoint.model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class YMLAccessor extends Accessor {

	@Override
	public void loadFile(Presentation presentation, String fileName)
			throws IOException {

		try (FileReader fileReader = new FileReader(fileName)) {
			Yaml yaml = new Yaml();
			Map<String, Object> yamlData = yaml.load(fileReader);

			String showTitle = (String) yamlData.get("showtitle");
			presentation.setTitle(showTitle);

			List<Map<String, Object>> slideList;
			slideList = (List<Map<String, Object>>) yamlData.get("slides");
			for (Map<String, Object> slideData : slideList) {
				String slideTitle = (String) slideData.get("title");
				Slide slide = new Slide();
				slide.setTitle(slideTitle);

				List<Map<String, Object>> slideItems;
				slideItems = (List<Map<String, Object>>) slideData.get("items");
				for (Map<String, Object> itemData : slideItems) {
					String itemType = (String) itemData.get("type");
					int level = (int) itemData.get("level");

					if (itemType.equals("text")) {
						String textContent = (String) itemData.get("content");
						TextItem textItem = new TextItem(level, textContent);
						slide.append(textItem);
					} else if (itemType.equals("image")) {
						String imagePath = (String) itemData.get("path");
						BitmapItem bitmapItem = new BitmapItem(level, imagePath);
						slide.append(bitmapItem);
					}
				}

				presentation.append(slide);
			}
		}
	}

	@Override
	public void saveFile(Presentation presentation, String fileName)
			throws IOException {

		try (FileWriter file = new FileWriter(fileName)) {

			file.write("showtitle: " + presentation.getTitle());
			file.write("\nslides: ");

			List<Slide> slides = presentation.getSlides();
			for (Slide slide : slides) {
				file.write("\n  - title: " + slide.getTitle());
				for (SlideItem item : slide.getItems()) {
					if (item instanceof TextItem) {
						file.write("\n    - text: " + ((TextItem) item).getText());
					} else if (item instanceof BitmapItem) {
						file.write("\n    - image: " + ((BitmapItem) item).getName());
					} else {
						file.write("\n    - Ignoring " + item);
					}
					file.write("\n    - level: " + item.getLevel());
				}
			}
		}
	}
}