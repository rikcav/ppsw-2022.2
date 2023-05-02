package br.upe.ppsw.jabberpoint.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONAccessor extends Accessor {

	@Override
	public void loadFile(Presentation presentation, String fileName)
			throws IOException {

		try (FileReader fileReader = new FileReader(fileName)) {
			Gson gson = new Gson();
			Type type = new TypeToken<Map<String, Object>>() {
			}.getType();
			Map<String, Object> jsonData = gson.fromJson(fileReader, type);

			String showTitle = (String) jsonData.get("showtitle");
			presentation.setTitle(showTitle);

			List<Map<String, Object>> slideList;
			slideList = (List<Map<String, Object>>) jsonData.get("slides");
			for (Map<String, Object> slideData : slideList) {
				String slideTitle = (String) slideData.get("title");
				Slide slide = new Slide();
				slide.setTitle(slideTitle);

				List<Map<String, Object>> slideItems;
				slideItems = (List<Map<String, Object>>) slideData.get("items");
				for (Map<String, Object> itemData : slideItems) {
					String itemType = (String) itemData.get("type");
					int level = ((Double) itemData.get("level")).intValue();

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

		try (FileWriter fileWriter = new FileWriter(fileName)) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			Map<String, Object> jsonData = new HashMap<>();
			jsonData.put("showtitle", presentation.getTitle());

			List<Slide> slides = presentation.getSlides();
			List<Map<String, Object>> slideList = new ArrayList<>();
			for (Slide slide : slides) {
				Map<String, Object> slideData = new HashMap<>();
				slideData.put("title", slide.getTitle());

				List<Map<String, Object>> slideItems = new ArrayList<>();
				for (SlideItem item : slide.getItems()) {
					Map<String, Object> itemData = new HashMap<>();
					if (item instanceof TextItem) {
						itemData.put("type", "text");
						itemData.put("content", ((TextItem) item).getText());
					} else if (item instanceof BitmapItem) {
						itemData.put("type", "image");
						itemData.put("path", ((BitmapItem) item).getName());
					}
					itemData.put("level", item.getLevel());
					slideItems.add(itemData);
				}

				slideData.put("items", slideItems);
				slideList.add(slideData);
			}

			jsonData.put("slides", slideList);
			gson.toJson(jsonData, fileWriter);
		}
	}
}
