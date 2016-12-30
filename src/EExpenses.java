import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;

/**
 * Created by Matthew on 2016-12-16.
 */
public class EExpenses {

    @SerializedName("liczba_pol")
    int liczba_pol;

    @SerializedName("liczba_rocznikow")
    int liczba_rocznikow;

    LinkedList<Points> pointsList;
    LinkedList<Years> yearsList;
    /*
        Lists of small expenses
        List of other expenses
        .
        .s
        .
     */
}
