package gui.menu.action;

import java.rmi.RemoteException;

/**
 *
 * @author Raven
 */
public interface MenuEvent {

    public void menuSelected(int index, int subIndex, MenuAction action) throws RemoteException;
}
