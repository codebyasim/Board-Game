//package application.usecase.endgame;
//
//public interface Provided {
//    void end();
//}

package application.usecase.endgame;

public interface Provided {
    Response handle(Request request);
}