public class SchuhAuswahl {
    class Auswahl {
        private String art;
        private String marke;
        private String farbe;
        private double groesse;
        private boolean leder;
        private double preis;

        public Auswahl(String art, String marke, String farbe, double groesse, boolean leder, double preis) {
            this.art = art;
            this.marke = marke;
            this.farbe = farbe;
            this.leder = leder;
            this.groesse = groesse;
            this.preis = preis;
        }

        public String getArt () {
            return art;
        }

        public String getMarke() {
            return marke;
        }

        public String getFarbe () {
            return farbe;
        }


        public double getGroeße () {
            return groesse;
        }


        public boolean isLeder() {
            return leder;
        }

        public double getPreis() {
            return preis;
        }

    }



}
