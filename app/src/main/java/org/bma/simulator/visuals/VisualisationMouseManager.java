package org.bma.simulator.visuals;

import org.bma.simulator.datamodel.UserNode;
import org.graphstream.ui.graphicGraph.GraphicGraph;
import org.graphstream.ui.swing_viewer.util.MouseOverMouseManager;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerListener;
import org.graphstream.ui.view.ViewerPipe;
import org.graphstream.ui.view.util.InteractiveElement;
import org.graphstream.ui.view.util.MouseManager;

import java.util.EnumSet;

public class VisualisationMouseManager implements ViewerListener {

    protected boolean loop = true;

    public VisualisationMouseManager() {

        // The default action when closing the view is to quit
        // the program.
        VisualisationGraph.getGraphViewer().setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);

        // We connect back the viewer to the graph,
        // the graph becomes a sink for the viewer.
        // We also install us as a viewer listener to
        // intercept the graphic events.
        ViewerPipe fromViewer = VisualisationGraph.getGraphViewer().newViewerPipe();
        fromViewer.addViewerListener(this);
        fromViewer.addSink(VisualisationGraph.getGraph());

        // Then we need a loop to do our work and to wait for events.
        // In this loop we will need to call the
        // pump() method before each use of the graph to copy back events
        // that have already occurred in the viewer thread inside
        // our thread.

        while (loop) {
            fromViewer.pump(); // or fromViewer.blockingPump(); in the nightly builds

            // here your simulation code.

            // You do not necessarily need to use a loop, this is only an example.
            // as long as you call pump() before using the graph. pump() is non
            // blocking.  If you only use the loop to look at event, use blockingPump()
            // to avoid 100% CPU usage. The blockingPump() method is only available from
            // the nightly builds.
        }
    }

    @Override
    public void viewClosed(String id) {
        loop = false;
    }

    public void buttonPushed(String id) {
        new NodeDataVisualisationPanel(VisualisationGraph.getGraph().getNode(id).getAttribute("data", UserNode.class).getData(), "Node Data");
    }

    public void buttonReleased(String id) {
    }

    public void mouseOver(String id) {
        System.out.println("Need the Mouse Options to be activated");
    }

    public void mouseLeft(String id) {
        System.out.println("Need the Mouse Options to be activated");
    }
}
