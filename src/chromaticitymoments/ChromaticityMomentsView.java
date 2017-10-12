/*
 * ChromaticityMomentsView.java
 */

package chromaticitymoments;

import chromaticitymoments.Algorithms.ChromaticityMoments;
import chromaticitymoments.Algorithms.Metric;
import chromaticitymoments.Util.ImageCM;
import chromaticitymoments.Util.PPM;
import chromaticitymoments.Util.BufferedImageBuilder;
import chromaticitymoments.Util.OutputObjects;
import chromaticitymoments.Util.ProcesamientoDeImagenes;
import chromaticitymoments.Util.ReadObjectInFile;
import chromaticitymoments.Util.Util;
import chromaticitymoments.Util.Utilitarios;
import java.awt.Color;
import java.awt.Image;
import java.io.UnsupportedEncodingException;
import javax.swing.ListModel;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
//import javax.media.jai.*;
import javax.swing.DefaultListModel;

/**
 * The application's main frame.
 */
public class ChromaticityMomentsView extends FrameView {
    private ArrayList<ImageCM> alICM;
    private PanHistograma phRQuery;
    private PanHistograma phBQuery;
    private PanHistograma phGQuery;

    private PanHistograma phRRetrieved;
    private PanHistograma phBRetrieved;
    private PanHistograma phGRetrieved;
    private static final String IMAGES_DIR = "images/";

    public ChromaticityMomentsView(SingleFrameApplication app) {
        super(app);

        initComponents();
        phRQuery=new PanHistograma("Red");
        this.panHistogramas1.add(phRQuery);

        phBQuery=new PanHistograma("Blue");
        this.panHistogramas1.add(phBQuery);

        phGQuery=new PanHistograma("Green");
        this.panHistogramas1.add(phGQuery);

        phRRetrieved=new PanHistograma("Red");  
        this.panHistRetrieved.add(phRRetrieved);

        phBRetrieved=new PanHistograma("Blue");
        this.panHistRetrieved.add(phBRetrieved);

        phGRetrieved=new PanHistograma("Green");
        this.panHistRetrieved.add(phGRetrieved);

        this.readImages();
        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = ChromaticityMomentsApp.getApplication().getMainFrame();
            aboutBox = new ChromaticityMomentsAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        ChromaticityMomentsApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jlImages = new javax.swing.JList();
        btnSearch = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        panRetrievedImages = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        panImage1 = new chromaticitymoments.PanImage();
        panImage5 = new chromaticitymoments.PanImage();
        panImage4 = new chromaticitymoments.PanImage();
        panImage3 = new chromaticitymoments.PanImage();
        panImage2 = new chromaticitymoments.PanImage();
        panIzq = new javax.swing.JPanel();
        jSplitPane3 = new javax.swing.JSplitPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        panImageQuery = new chromaticitymoments.PanImage();
        jPanel4 = new javax.swing.JPanel();
        jSplitPane4 = new javax.swing.JSplitPane();
        panHistogramas1 = new javax.swing.JPanel();
        panHistRetrieved = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new java.awt.BorderLayout());

        jSplitPane1.setName("jSplitPane1"); // NOI18N

        jPanel2.setName("jPanel2"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(chromaticitymoments.ChromaticityMomentsApp.class).getContext().getResourceMap(ChromaticityMomentsView.class);
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel5.border.title"))); // NOI18N
        jPanel5.setName("jPanel5"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jlImages.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Bark.0000.ppm", "Bark.0001.ppm" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jlImages.setName("jlImages"); // NOI18N
        jlImages.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlImagesValueChanged(evt);
            }
        });
        jlImages.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jlImagesPropertyChange(evt);
            }
        });
        jScrollPane2.setViewportView(jlImages);

        btnSearch.setText(resourceMap.getString("btnSearch.text")); // NOI18N
        btnSearch.setName("btnSearch"); // NOI18N
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSearch)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane1.setLeftComponent(jPanel2);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setLayout(new java.awt.BorderLayout());

        jSplitPane2.setDividerLocation(350);
        jSplitPane2.setName("jSplitPane2"); // NOI18N

        panRetrievedImages.setName("panRetrievedImages"); // NOI18N
        panRetrievedImages.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jPanel6.setBackground(resourceMap.getColor("jPanel6.background")); // NOI18N
        jPanel6.setName("jPanel6"); // NOI18N
        jPanel6.setLayout(new java.awt.GridLayout(5, 1));

        panImage1.setName("panImage1"); // NOI18N
        panImage1.setPreferredSize(new java.awt.Dimension(250, 240));
        panImage1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panImage1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panImage1Layout = new javax.swing.GroupLayout(panImage1);
        panImage1.setLayout(panImage1Layout);
        panImage1Layout.setHorizontalGroup(
            panImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 293, Short.MAX_VALUE)
        );
        panImage1Layout.setVerticalGroup(
            panImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 252, Short.MAX_VALUE)
        );

        jPanel6.add(panImage1);

        panImage5.setName("panImage5"); // NOI18N
        panImage5.setPreferredSize(new java.awt.Dimension(250, 240));
        panImage5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panImage5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panImage5Layout = new javax.swing.GroupLayout(panImage5);
        panImage5.setLayout(panImage5Layout);
        panImage5Layout.setHorizontalGroup(
            panImage5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 293, Short.MAX_VALUE)
        );
        panImage5Layout.setVerticalGroup(
            panImage5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 252, Short.MAX_VALUE)
        );

        jPanel6.add(panImage5);

        panImage4.setName("panImage4"); // NOI18N
        panImage4.setPreferredSize(new java.awt.Dimension(250, 240));
        panImage4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panImage4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panImage4Layout = new javax.swing.GroupLayout(panImage4);
        panImage4.setLayout(panImage4Layout);
        panImage4Layout.setHorizontalGroup(
            panImage4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 293, Short.MAX_VALUE)
        );
        panImage4Layout.setVerticalGroup(
            panImage4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 252, Short.MAX_VALUE)
        );

        jPanel6.add(panImage4);

        panImage3.setName("panImage3"); // NOI18N
        panImage3.setPreferredSize(new java.awt.Dimension(250, 240));
        panImage3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panImage3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panImage3Layout = new javax.swing.GroupLayout(panImage3);
        panImage3.setLayout(panImage3Layout);
        panImage3Layout.setHorizontalGroup(
            panImage3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 293, Short.MAX_VALUE)
        );
        panImage3Layout.setVerticalGroup(
            panImage3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 252, Short.MAX_VALUE)
        );

        jPanel6.add(panImage3);

        panImage2.setName("panImage2"); // NOI18N
        panImage2.setPreferredSize(new java.awt.Dimension(250, 240));
        panImage2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panImage2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panImage2Layout = new javax.swing.GroupLayout(panImage2);
        panImage2.setLayout(panImage2Layout);
        panImage2Layout.setHorizontalGroup(
            panImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 293, Short.MAX_VALUE)
        );
        panImage2Layout.setVerticalGroup(
            panImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 252, Short.MAX_VALUE)
        );

        jPanel6.add(panImage2);

        jScrollPane1.setViewportView(jPanel6);

        panRetrievedImages.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jSplitPane2.setRightComponent(panRetrievedImages);

        panIzq.setName("panIzq"); // NOI18N
        panIzq.setLayout(new java.awt.BorderLayout());

        jSplitPane3.setDividerLocation(200);
        jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane3.setName("jSplitPane3"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        panImageQuery.setName("panImageQuery"); // NOI18N

        javax.swing.GroupLayout panImageQueryLayout = new javax.swing.GroupLayout(panImageQuery);
        panImageQuery.setLayout(panImageQueryLayout);
        panImageQueryLayout.setHorizontalGroup(
            panImageQueryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
        );
        panImageQueryLayout.setVerticalGroup(
            panImageQueryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 194, Short.MAX_VALUE)
        );

        jScrollPane3.setViewportView(panImageQuery);

        jSplitPane3.setTopComponent(jScrollPane3);

        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setLayout(new java.awt.BorderLayout());

        jSplitPane4.setDividerLocation(100);
        jSplitPane4.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane4.setName("jSplitPane4"); // NOI18N

        panHistogramas1.setName("panHistogramas1"); // NOI18N
        jSplitPane4.setTopComponent(panHistogramas1);

        panHistRetrieved.setName("panHistRetrieved"); // NOI18N
        jSplitPane4.setRightComponent(panHistRetrieved);

        jPanel4.add(jSplitPane4, java.awt.BorderLayout.CENTER);

        jSplitPane3.setRightComponent(jPanel4);

        panIzq.add(jSplitPane3, java.awt.BorderLayout.CENTER);

        jSplitPane2.setLeftComponent(panIzq);

        jPanel3.add(jSplitPane2, java.awt.BorderLayout.CENTER);

        jSplitPane1.setRightComponent(jPanel3);

        jPanel1.add(jSplitPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 905, Short.MAX_VALUE)
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(chromaticitymoments.ChromaticityMomentsApp.class).getContext().getActionMap(ChromaticityMomentsView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 945, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 749, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseClicked
        // TODO add your handling code here:


        
        ImageCM icmQ=null;

        String selectedImage=(String)this.jlImages.getSelectedValue();
        for (ImageCM imageCM : alICM) {
            if(imageCM.getImageName().compareTo(selectedImage)==0)
                icmQ=imageCM;
        }
        alICM=Metric.distance(icmQ, alICM);
        
        int numRetrieved=5;
        
        PanImage[] aP=new PanImage[numRetrieved];
        aP[0]=this.panImage1;
        aP[1]=this.panImage2;
        aP[2]=this.panImage3;
        aP[3]=this.panImage4;
        aP[4]=this.panImage5;
        
        for (int i=0;i<numRetrieved;i++) {
            try {
                //            String imageName=alICM.get(i).getImageName();
                //            Image img=new ImageIcon(imageName).getImage();
                PPM p = new PPM(IMAGES_DIR + alICM.get(i).getImageName());
                Image img = p.getImage();
                aP[i].setImage(img,alICM.get(i).getImageName());
                aP[i].repaint();
            } catch (IOException ex) {
                Logger.getLogger(ChromaticityMomentsView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ChromaticityMomentsView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }//GEN-LAST:event_btnSearchMouseClicked

    private void jlImagesPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jlImagesPropertyChange
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jlImagesPropertyChange

    private void jlImagesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlImagesValueChanged
        // TODO add your handling code here:
        String imageName=  (String)this.jlImages.getSelectedValue();//txtImage.getText();
        PPM p;
        try {
            p = new PPM(IMAGES_DIR + imageName);
            Image img = p.getImage();
            this.panImageQuery.setImage(img, imageName);
            panImageQuery.repaint();
            this.cargarHistogramasQuery();
        } catch (IOException ex) {
            Logger.getLogger(ChromaticityMomentsView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ChromaticityMomentsView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jlImagesValueChanged

    private void panImage1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panImage1MouseClicked
        // TODO add your handling code here:
        this.cargarHistogramasRetrieved(this.panImage1.getImageName());
}//GEN-LAST:event_panImage1MouseClicked

    private void panImage2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panImage2MouseClicked
        // TODO add your handling code here:
        this.cargarHistogramasRetrieved(this.panImage2.getImageName());
}//GEN-LAST:event_panImage2MouseClicked

    private void panImage5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panImage5MouseClicked
        // TODO add your handling code here:
        this.cargarHistogramasRetrieved(this.panImage5.getImageName());
    }//GEN-LAST:event_panImage5MouseClicked

    private void panImage4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panImage4MouseClicked
        // TODO add your handling code here:
        this.cargarHistogramasRetrieved(this.panImage4.getImageName());
    }//GEN-LAST:event_panImage4MouseClicked

    private void panImage3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panImage3MouseClicked
        // TODO add your handling code here:
        this.cargarHistogramasRetrieved(this.panImage3.getImageName());
    }//GEN-LAST:event_panImage3MouseClicked

    public void readImages(){
        DefaultListModel mod = new DefaultListModel();
        ReadObjectInFile ro=new ReadObjectInFile();
        ro.openFile();
        alICM=(ArrayList<ImageCM>) ro.readRecords();
        ImageCM icmQ=null;
        int i=0;
        for (ImageCM imageCM : alICM) {
            mod.add(i++, imageCM.getImageName());
        }
        this.jlImages.setModel(mod);
    }

    public void cargarHistogramasQuery(){
         BufferedImage imagem = Util.getImage(IMAGES_DIR + (String)this.jlImages.getSelectedValue());
            if(imagem!=null)
            {


            int w = imagem.getWidth();
            int h = imagem.getHeight();
            int[] pixels = imagem.getRGB(0, 0, w, h, null, 0, w);
            Color c;
            int [][] R=new int[h][w];
            int [][] G=new int[h][w];
            int [][] B=new int[h][w];

            for (int row = 0; row < h; row++) {
                for (int col = 0; col < w; col++) {
                    c=new Color(imagem.getRGB(col, row));

                    R[row][col]=c.getRed();
                    G[row][col]=c.getGreen();//((DirectColorModel)imagem.getColorModel()).getGreen(i*h+j);
                    B[row][col]=c.getBlue();//((DirectColorModel)imagem.getColorModel()).getBlue(i*h+j);
                }
            }
            double[] histR=ProcesamientoDeImagenes.getHistograma(R,false);
            phRQuery.setVNumeroTonos(histR);
            phRQuery.repaint();

            double[] histB=ProcesamientoDeImagenes.getHistograma(B,false);
            phBQuery.setVNumeroTonos(histB);
            phBQuery.repaint();

            double[] histG=ProcesamientoDeImagenes.getHistograma(G,false);
            phGQuery.setVNumeroTonos(histG);
            phGQuery.repaint();
        }
    }

    public void cargarHistogramasRetrieved(String name){
         BufferedImage imagem = Util.getImage(IMAGES_DIR + name);
            if(imagem!=null)
            {


            int w = imagem.getWidth();
            int h = imagem.getHeight();
            int[] pixels = imagem.getRGB(0, 0, w, h, null, 0, w);
            Color c;
            int [][] R=new int[h][w];
            int [][] G=new int[h][w];
            int [][] B=new int[h][w];

            for (int row = 0; row < h; row++) {
                for (int col = 0; col < w; col++) {
                    c=new Color(imagem.getRGB(col, row));

                    R[row][col]=c.getRed();
                    G[row][col]=c.getGreen();//((DirectColorModel)imagem.getColorModel()).getGreen(i*h+j);
                    B[row][col]=c.getBlue();//((DirectColorModel)imagem.getColorModel()).getBlue(i*h+j);
                }
            }
            double[] histR=ProcesamientoDeImagenes.getHistograma(R,false);
            phRRetrieved.setVNumeroTonos(histR);
            phRRetrieved.repaint();

            double[] histB=ProcesamientoDeImagenes.getHistograma(B,false);
            phBRetrieved.setVNumeroTonos(histB);
            phBRetrieved.repaint();

            double[] histG=ProcesamientoDeImagenes.getHistograma(G,false);
            phGRetrieved.setVNumeroTonos(histG);
            phGRetrieved.repaint();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JSplitPane jSplitPane4;
    private javax.swing.JList jlImages;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPanel panHistRetrieved;
    private javax.swing.JPanel panHistogramas1;
    private chromaticitymoments.PanImage panImage1;
    private chromaticitymoments.PanImage panImage2;
    private chromaticitymoments.PanImage panImage3;
    private chromaticitymoments.PanImage panImage4;
    private chromaticitymoments.PanImage panImage5;
    private chromaticitymoments.PanImage panImageQuery;
    private javax.swing.JPanel panIzq;
    private javax.swing.JPanel panRetrievedImages;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;


}
