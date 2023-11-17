package org.bma.simulator.visuals.data;

import org.bma.simulator.datamodel.UserNode;
import org.bma.simulator.utils.ObjectArrayToCSVUtils;
import org.bma.simulator.visuals.VisualisationGraph;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ResultDataVisualisationPanel extends DataVisualisationPanel {

    public ResultDataVisualisationPanel(Object[][] data, String title) {
        super(data, title);
        panel.add(getDownloadButton());
        setVisible(true);
    }

    private String showFolderSelectionDialog() {
        JFileChooser folderChooser = new JFileChooser();
        folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = folderChooser.showDialog(this, "Select Folder");

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = folderChooser.getSelectedFile();
            String absolutePath = selectedFolder.getAbsolutePath();
            // Ensure the path ends with the appropriate separator
            if (!absolutePath.endsWith(File.separator)) {
                absolutePath += File.separator;
            }
            return absolutePath;
        }
        return null;
    }

    private void downloadCSV() throws IOException {
        String path = showFolderSelectionDialog();
        if (path != null) {
            Object[][] data = VisualisationGraph.getGraph().nodes().map(node -> node.getAttribute("data", UserNode.class).getData()).flatMap(Arrays::stream).toArray(Object[][]::new);
            ObjectArrayToCSVUtils.writeDataToCSV(data, path + "data.csv");
        }

    }

    private JButton getDownloadButton() {
        JButton downloadButton = new JButton("Download CSV");
        downloadButton.addActionListener(e -> {
            try {
                downloadCSV();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        return downloadButton;
    }
}
