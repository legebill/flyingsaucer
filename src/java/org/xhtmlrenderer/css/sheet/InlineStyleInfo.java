/*
 * InlineStyleInfo.java
 * Copyright (c) 2004, 2005 Torbj�rn Gannholm
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.	See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 *
 */
package org.xhtmlrenderer.css.sheet;



/**
 *
 * @author   Torbj�rn Gannholm
 */
public class InlineStyleInfo {
    /** Description of the Field */
    private String style;

    /** Description of the Field */
    private StylesheetInfo info;

    /**
     * Sets the style attribute of the InlineStyleInfo object
     *
     * @param style  The new style value
     */
    public void setStyle( String style ) {
        this.style = style;
    }

    /**
     * Sets the info attribute of the InlineStyleInfo object
     *
     * @param info  The new info value
     */
    public void setInfo( StylesheetInfo info ) {
        this.info = info;
    }

    /**
     * Gets the style attribute of the InlineStyleInfo object
     *
     * @return   The style value
     */
    public String getStyle() {
        return style;
    }

    /**
     * Gets the info attribute of the InlineStyleInfo object
     *
     * @return   The info value
     */
    public StylesheetInfo getInfo() {
        return info;
    }
}

