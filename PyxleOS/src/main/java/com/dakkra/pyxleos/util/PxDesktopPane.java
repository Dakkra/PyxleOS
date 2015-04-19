package com.dakkra.pyxleos.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JDesktopPane;
import javax.swing.JScrollBar;
import javax.swing.JViewport;
import javax.swing.Scrollable;
import javax.swing.SwingUtilities;

public class PxDesktopPane extends JDesktopPane implements Scrollable {

	private static final long serialVersionUID = -6600842930646363073L;

	private ComponentListener childComponentHandler = new ChildComponentHandler();

	@Override
	protected void addImpl(Component comp, Object constraints, int index) {
		super.addImpl(comp, constraints, index);
		comp.addComponentListener(childComponentHandler);
	}
	
	public void updateDesktopSize() {
		JViewport viewport = (JViewport) SwingUtilities.getAncestorOfClass(JViewport.class, this);
		if (viewport == null) return;

		Rectangle oldBounds = getBounds();
		Rectangle newBounds = new Rectangle();
		Rectangle viewBounds = viewport.getBounds();
		SwingUtilities.computeUnion(oldBounds.x, oldBounds.y, viewBounds.width, viewBounds.height, newBounds);

		for (Component component : getComponents()) {
			Rectangle cBounds = component.getBounds();
			SwingUtilities.computeUnion(cBounds.x, cBounds.y, cBounds.width, cBounds.height, newBounds);
		}

//		if (!newBounds.getLocation().equals(oldBounds.getLocation())) {
//			int deltaX = newBounds.x - oldBounds.x;
//			int deltaY = newBounds.y - oldBounds.y;
//			for (Component component : getComponents()) {
//				component.removeComponentListener(childComponentHandler);
//				
//				Point location = new Point(component.getLocation());
//				location.move(location.x + deltaX, location.y + deltaY);
//				component.setLocation(location);
//				
//				component.addComponentListener(childComponentHandler);
//			}
//			setLocation(newBounds.getLocation());
//		}
		setPreferredSize(newBounds.getSize());
	}

	// public Dimension getPreferredSize() {
	// return getBounds().getSize();
	// }

	// public Dimension getPreferredSize() {
	// JViewport viewport = (JViewport)
	// SwingUtilities.getAncestorOfClass(JViewport.class, this);
	//
	// Rectangle bounds = new Rectangle(viewport.getBounds());
	// bounds.setLocation(0, 0);
	//
	// for (Component component : getComponents()) {
	// Rectangle cBounds = component.getBounds();
	// SwingUtilities.computeUnion(cBounds.x, cBounds.y, cBounds.width,
	// cBounds.height, bounds);
	// }
	//
	// setLocation(bounds.x, bounds.y);
	//
	// return new Dimension(bounds.width, bounds.height);
	// }

	/**
	 * Returns the preferred size of the viewport for a view component. For
	 * example, the preferred size of a <code>JList</code> component is the size
	 * required to accommodate all of the cells in its list. However, the value
	 * of <code>preferredScrollableViewportSize</code> is the size required for
	 * <code>JList.getVisibleRowCount</code> rows. A component without any
	 * properties that would affect the viewport size should just return
	 * <code>getPreferredSize</code> here.
	 *
	 * @return the preferredSize of a <code>JViewport</code> whose view is this
	 *         <code>Scrollable</code>
	 * @see JViewport#getPreferredSize
	 */
	public Dimension getPreferredScrollableViewportSize() {
		return getPreferredSize();
	}

	/**
	 * Components that display logical rows or columns should compute the scroll
	 * increment that will completely expose one new row or column, depending on
	 * the value of orientation. Ideally, components should handle a partially
	 * exposed row or column by returning the distance required to completely
	 * expose the item.
	 * <p>
	 * Scrolling containers, like JScrollPane, will use this method each time
	 * the user requests a unit scroll.
	 *
	 * @param visibleRect
	 *            The view area visible within the viewport
	 * @param orientation
	 *            Either SwingConstants.VERTICAL or SwingConstants.HORIZONTAL.
	 * @param direction
	 *            Less than zero to scroll up/left, greater than zero for
	 *            down/right.
	 * @return The "unit" increment for scrolling in the specified direction.
	 *         This value should always be positive.
	 * @see JScrollBar#setUnitIncrement
	 */
	public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
		return 1;
	}

	/**
	 * Components that display logical rows or columns should compute the scroll
	 * increment that will completely expose one block of rows or columns,
	 * depending on the value of orientation.
	 * <p>
	 * Scrolling containers, like JScrollPane, will use this method each time
	 * the user requests a block scroll.
	 *
	 * @param visibleRect
	 *            The view area visible within the viewport
	 * @param orientation
	 *            Either SwingConstants.VERTICAL or SwingConstants.HORIZONTAL.
	 * @param direction
	 *            Less than zero to scroll up/left, greater than zero for
	 *            down/right.
	 * @return The "block" increment for scrolling in the specified direction.
	 *         This value should always be positive.
	 * @see JScrollBar#setBlockIncrement
	 */
	public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
		return 10;
	}

	/**
	 * Return true if a viewport should always force the width of this
	 * <code>Scrollable</code> to match the width of the viewport. For example a
	 * normal text view that supported line wrapping would return true here,
	 * since it would be undesirable for wrapped lines to disappear beyond the
	 * right edge of the viewport. Note that returning true for a Scrollable
	 * whose ancestor is a JScrollPane effectively disables horizontal
	 * scrolling.
	 * <p>
	 * Scrolling containers, like JViewport, will use this method each time they
	 * are validated.
	 *
	 * @return True if a viewport should force the Scrollables width to match
	 *         its own.
	 */
	public boolean getScrollableTracksViewportWidth() {
		return false;
	}

	/**
	 * Return true if a viewport should always force the height of this
	 * Scrollable to match the height of the viewport. For example a columnar
	 * text view that flowed text in left to right columns could effectively
	 * disable vertical scrolling by returning true here.
	 * <p>
	 * Scrolling containers, like JViewport, will use this method each time they
	 * are validated.
	 *
	 * @return True if a viewport should force the Scrollables height to match
	 *         its own.
	 */
	public boolean getScrollableTracksViewportHeight() {
		return false;
	}

	private class ChildComponentHandler implements ComponentListener {

		@Override
		public void componentResized(ComponentEvent event) {
		}

		@Override
		public void componentMoved(ComponentEvent event) {
			updateDesktopSize();
		}

		@Override
		public void componentShown(ComponentEvent event) {
		}

		@Override
		public void componentHidden(ComponentEvent event) {
		}

	}

}
