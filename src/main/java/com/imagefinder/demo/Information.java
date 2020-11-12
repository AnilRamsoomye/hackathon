package com.imagefinder.demo;

import java.util.ArrayList;

public class Information {

		
		public ArrayList<String> images;
		public ArrayList<String> icons;
		
		public Information() {
			images = new ArrayList<String>();
			icons = new ArrayList<String>();
			
		}

		public ArrayList<String> getImages() {
			return images;
		}

		public void setImages(ArrayList<String> images) {
			this.images = images;
		}

		public ArrayList<String> getIcons() {
			return icons;
		}

		public void setIcons(ArrayList<String> icons) {
			this.icons = icons;
		}
		
		
	
}
