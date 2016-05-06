package de.kreth.formatter.table;

import java.awt.*;
import java.text.NumberFormat;

public class Column {

	public enum VerticalAlign {
		LEFT, RIGHT, CENTER
	}

	private String columnName;
	private NumberFormat format;
	private int columnWidth;
	private VerticalAlign vAlign;

	private Column(Builder b) {
		this.columnName = b.columnName;
		this.format = b.format;
		this.columnWidth = b.columnWidth;
		this.vAlign = b.vAlign;
	}

	public <T> void paint(Graphics graphics, Rectangle bounds, T content) {
		String c;
		if (content instanceof Number)
			c = getFormatted((Number) content);
		else if (content instanceof String)
			c = getFormatted((String) content);
		else
			c = "";

		FontMetrics fontMetrics = graphics.getFontMetrics();
		int x = calcXPos(c, bounds, fontMetrics);
		int y = fontMetrics.getHeight() + bounds.y;
		Shape clip = graphics.getClip();
		graphics.clipRect(bounds.x, bounds.y, bounds.width, bounds.height);
		graphics.drawString(c, x, y);
		graphics.setClip(clip);
	}

	protected int calcXPos(String c, Rectangle bounds, FontMetrics fontMetrics) {
		int length = fontMetrics.stringWidth(c);
		switch (vAlign) {
		case CENTER:
			return bounds.x + (bounds.width - length) / 2;
		case RIGHT:
			return bounds.x + bounds.width - length;
		case LEFT:
		default:
			return bounds.x;
		}

	}

	private String getFormatted(String textContent) {

		if (textContent != null)
			return textContent;

		return "";
	}

	private String getFormatted(Number numberContent) {

		if (format != null && numberContent != null) {
			return format.format(numberContent);
		}

		throw new IllegalArgumentException("Column not prepared for Number Content, set Numberformatter in Builder");
	}

	public String getColumnName() {
		return columnName;
	}

	public int getColumnWidth() {
		return columnWidth;
	}

	public static class Builder {

		private String columnName = null;
		private NumberFormat format = null;
		private int columnWidth = 20;
		private VerticalAlign vAlign = VerticalAlign.LEFT;

		public Builder setColumnWidth(int columnWidth) {
			this.columnWidth = columnWidth;
			return this;
		}

		public Builder setVerticalAlign(VerticalAlign vAlign) {
			this.vAlign = vAlign;
			return this;
		}

		public Builder setColumnName(String columnName) {
			this.columnName = columnName;
			return this;
		}

		public Builder setFormat(NumberFormat format) {
			this.format = format;
			return this;
		}

		public Column build() {
			return new Column(this);
		}

	}

}
