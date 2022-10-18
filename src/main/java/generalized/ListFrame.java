package generalized;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

class ListFrame extends JFrame implements Observer {
   JList list; 
   JPanel p; 
   JScrollPane lsp; 
   JListData listData;

   public ListFrame()    {
    super("Color List"); 
    //put panel into the frmae 
    p = new JPanel(true); 
    getContentPane().add("Center",  p); 
    p.setLayout(new BorderLayout()); 
    //Tell the old.Subject we are interested
//    s.addObserver(this);
    //Create the list
    listData = new JListData();  //the list model
    list = new JList(listData);  //the visual list 
    lsp = new JScrollPane();     //the scroller 
    lsp.getViewport().add(list); 
    p.add("Center", lsp); 
    lsp.setPreferredSize(new Dimension(100,100)); 
    setBounds(250, 100, 100, 100); 
    setVisible(true); 
   }

   //--------------------------------

    @Override
    public void update(Observable o, Object arg) {
        listData.addElement((String)arg);
    }
}
