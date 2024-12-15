package com.nyloer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.util.Text;

import java.awt.Color;
import org.apache.commons.lang3.StringUtils;


@Slf4j
public class CustomFontConfig
{
	private static final CustomFontConfig INSTANCE = new CustomFontConfig();
	@Getter
	private final HashMap<String, Color> colorSettings = new HashMap<>();
	@Getter
	private final HashMap<Integer, Integer> darkenWavesWithOffsets = new HashMap<>();
	private final String wavesAndOffsetsPattern = "^(\\d{1,2}:\\d{1,2})";
	private final String wavesPattern = "^(\\d{1,2})";
	private final String invalidCharacters = "[^\\d,:]";

	public Color getColor(String fontConfigKey)
	{
		return colorSettings.get(fontConfigKey);
	}

	public void parse(NyloerConfig config)
	{
		parseCustomDarkenConfig(config);
		String input = config.customFontConfig();
		if (input == null || input.isEmpty())
		{
			return;
		}
		for (String entry : Text.fromCSV(input))
		{
			try
			{
				String[] parts = entry.split(":");
				colorSettings.put(
					Integer.parseInt(parts[0].trim()) + "-" + parts[1].trim(),
					Color.decode(parts[2].trim())
				);
			}
			catch (Exception e)
			{
				log.debug("Parse exception: \"" + entry + "\"\n" + e.getMessage());
			}
		}
	}

	public static CustomFontConfig getInstance()
	{
		return INSTANCE;
	}

	private void parseCustomDarkenConfig(NyloerConfig config)
	{
		darkenWavesWithOffsets.clear();
		String darkenWavesString = config.customDarkenConfig();
		if (StringUtils.isNotBlank(darkenWavesString))
		{
			Arrays.stream(darkenWavesString
					.replaceAll(invalidCharacters, "")
					.split(","))
				.forEach(str -> {
					if (str.matches(wavesAndOffsetsPattern))
					{
						List<Integer> waveOffset = Arrays.stream(str.split(":"))
							.map(Integer::parseInt)
							.collect(Collectors.toList());
						darkenWavesWithOffsets.putIfAbsent(waveOffset.get(0), waveOffset.get(1));
					}
					else if (str.matches(wavesPattern))
					{
						darkenWavesWithOffsets.putIfAbsent(Integer.parseInt(str), 0);
					}
				});
		}
	}
}