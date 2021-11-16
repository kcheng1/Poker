module com.cards.poker {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.cards.poker to javafx.fxml;
    exports com.cards.poker;
}