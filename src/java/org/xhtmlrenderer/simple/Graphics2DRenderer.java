/*
 * {{{ header & license
 * Copyright (c) 2004, 2005 Joshua Marinacci
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
 * }}}
 */
package org.xhtmlrenderer.simple;

import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import org.w3c.dom.*;
import org.xhtmlrenderer.extend.*;


/**
 * <p>
 *
 * Graphics2DRenderer supports headless rendering of XHTML documents. It
 * supports the setDocument, layout, and render methods XHTMLPanel, but the
 * easiest way to use it is with the static utility methods. For example, to
 * render a document in an image that is 600 pixels wide use the renderToImage
 * method like this:</p> <pre>
 *URL url = new URL("test.xhtml");
 *BufferedImage img = Graphics2DRenderer.renderToImage( url, width);
 *</pre> <p>
 *
 * </p>
 *
 * @author   Joshua Marinacci
 */

public class Graphics2DRenderer {
    /** Description of the Field */
    protected XHTMLPanel panel;
    /** Description of the Field */
    protected Dimension dim;

    /** Constructor for the Graphics2DRenderer object */
    public Graphics2DRenderer() {
        panel = new XHTMLPanel();
        panel.setThreadedLayout( false );
    }

    // ASK maybe we could change the graphics2d to be a font rendering context?
    /**
     * lay out the document with the desired size. don't paint though.
     *
     * @param g2   PARAM
     * @param dim  PARAM
     */
    public void layout( Graphics2D g2, Dimension dim ) {
        this.dim = dim;
        panel.setSize( dim );
        panel.startLayout( g2 );
    }


    /**
     * actually draw the
     *
     * @param g2  PARAM
     */
    public void render( Graphics2D g2 ) {
        if ( g2.getClip() == null ) {
            g2.setClip( getMinimumSize() );
        }
        panel.paintComponent( g2 );
    }


    /**
     * set the document to be rendered.
     *
     * @param url            The new document value
     * @exception Exception  Throws
     */
    public void setDocument( URL url )
        throws Exception {
        panel.setDocument( url );
    }

    /**
     * Sets the document attribute of the Graphics2DRenderer object
     *
     * @param doc       The new document value
     * @param base_url  The new document value
     */
    public void setDocument( Document doc, URL base_url ) {
        panel.setDocument( doc, base_url );
    }

    /**
     * Sets the renderingContext attribute of the Graphics2DRenderer object
     *
     * @param ctx  The new renderingContext value
     */
    public void setRenderingContext( RenderingContext ctx ) {
        panel.setRenderingContext( ctx );
    }

    /**
     * return the size image needed to render the document without anything
     * going off the side. *could be different than the dimensions passed into
     * layout because of text that couldn't break or a table that's set to be
     * too big
     *
     * @return   The minimumSize value
     */
    public Rectangle getMinimumSize() {
        return new Rectangle( 0, 0,
                (int)panel.getIntrinsicSize().getWidth(),
                (int)panel.getIntrinsicSize().getHeight() );
    }

    /**
     * Gets the renderingContext attribute of the Graphics2DRenderer object
     *
     * @return   The renderingContext value
     */
    public RenderingContext getRenderingContext() {
        return panel.getRenderingContext();
    }

    /**
     * a static utility method to automatically create an image the right size,
     * then layout and render the document
     *
     * @param url            PARAM
     * @param width          PARAM
     * @param height         PARAM
     * @return               Returns
     * @exception Exception  Throws
     */
    public static BufferedImage renderToImage( URL url, int width, int height )
        throws Exception {
        Graphics2DRenderer g2r = new Graphics2DRenderer();
        g2r.setDocument( url );
        Dimension dim = new Dimension( width, height );
        BufferedImage buff = new BufferedImage( (int)dim.getWidth(), (int)dim.getHeight(), BufferedImage.TYPE_4BYTE_ABGR );
        Graphics2D g = (Graphics2D)buff.getGraphics();
        g2r.layout( g, dim );
        g2r.render( g );
        g.dispose();
        return buff;
    }

    /**
     * Description of the Method
     *
     * @param url            PARAM
     * @param width          PARAM
     * @return               Returns
     * @exception Exception  Throws
     */
    public static BufferedImage renderToImage( URL url, int width )
        throws Exception {
        Graphics2DRenderer g2r = new Graphics2DRenderer();
        g2r.setDocument( url );
        Dimension dim = new Dimension( width, 1000 );

        // do layout with temp buffer
        BufferedImage buff = new BufferedImage( (int)dim.getWidth(), (int)dim.getHeight(), BufferedImage.TYPE_4BYTE_ABGR );
        Graphics2D g = (Graphics2D)buff.getGraphics();
        g2r.layout( g, new Dimension( width, 1000 ) );
        g.dispose();

        // get size
        Rectangle rect = g2r.getMinimumSize();

        // render into real buffer
        buff = new BufferedImage( (int)rect.getWidth(), (int)rect.getHeight(), BufferedImage.TYPE_4BYTE_ABGR );
        g = (Graphics2D)buff.getGraphics();
        g2r.render( g );
        g.dispose();

        // return real buffer
        return buff;
    }

}

