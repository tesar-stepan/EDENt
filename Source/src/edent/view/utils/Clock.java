/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.view.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * from
 * http://www.leepoint.net/notes-java/examples/animation/41TextClock/30textclock2.html
 *
 * @author Fred Swartz, Copyleft 2005 - <a
 * href='http://opensource.org/licenses/mit-license.php'>MIT License</a>
 * @author Stepan Tesar, minor changes.
 */
public class Clock extends JLabel {
    private SimpleDateFormat formatter;
    private Timer m_t;

    public Clock() {
        this("HH:mm");
    }
    
    public Clock(String pattern){
        formatter = new SimpleDateFormat(pattern);
        //... Create a 1-second timer.
        m_t = new javax.swing.Timer(1000, new ClockTickAction());
        m_t.start();  // Start the timer
    }

    private class ClockTickAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //... Get the current time.
            Calendar now = Calendar.getInstance();
            String time = formatter.format(now.getTime());
            setText(time);
        }
    }
}
