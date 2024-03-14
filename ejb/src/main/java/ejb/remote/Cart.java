package ejb.remote;

import jakarta.ejb.Remote;

@Remote
public interface Cart {
    public void getDetails();
}
