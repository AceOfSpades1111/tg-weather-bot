package com.serj.image;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface ImageCreator<T> {
	byte[] getImageByteArray(Map<T, String> map, File imageFile) throws IOException;
}
