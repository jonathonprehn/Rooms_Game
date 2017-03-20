/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp.graphics;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;

/**
 *
 * @author Jonathon
 */
public class Music implements Runnable {

    private Thread thread;
    
    public Music(GameWindow win) {
        thread = new Thread(this);
        thread.start();
        win.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                thread.interrupt();
                
            }
            
        });
    }
    
    private static final int shiftAmount = 7;
    private static final int[] notes = new int[] {
        80+shiftAmount, 78+shiftAmount, 84+shiftAmount, 78+shiftAmount,
        80+0, 78+0, 84+0, 78+0
    };
    private static MidiMessage[][] notesMessages = new MidiMessage[notes.length][];
    
    @Override
    public void run() {
        long speed = 500;
       
        
        try {
            for (int i=0; i<notes.length; i++) {
                notesMessages[i] = new MidiMessage[] {
                    new ShortMessage(ShortMessage.NOTE_ON, 0, notes[i], 100),
                    new ShortMessage(ShortMessage.NOTE_OFF, 0, notes[i], 100)
                }; 
            }

            Synthesizer midi = MidiSystem.getSynthesizer();
            Receiver rec = midi.getReceiver();
            midi.open();
            int onNote = 0;
            
            rec.send(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 0, 14, 127), -1);
            
            while (midi.isOpen()) {
                rec.send(notesMessages[onNote][0], -1);
                Thread.sleep(speed);
                rec.send(notesMessages[onNote][1], -1);
                onNote++;
                if (onNote >= notes.length) {
                    onNote = 0;
                }
            }
        } catch(MidiUnavailableException mue) {
            System.err.println("No midi support, no music" + mue);
        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(Music.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Music.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
