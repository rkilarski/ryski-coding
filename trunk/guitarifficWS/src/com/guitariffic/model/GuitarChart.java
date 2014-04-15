package com.guitariffic.model;

public class GuitarChart implements Cloneable {
    private String id;
    private String chordName;
    private String chordPosition;
    private String chordFingering;
    private String chordFrets;
    private boolean isLeftHanded;

    public GuitarChart() {
    }

    public GuitarChart(String id, String chordName, String chordPosition, String chordFingering,
            String chordFrets, boolean isLeftHanded) {
        this.id = id;
        this.chordName = chordName;
        this.chordPosition = chordPosition;
        this.chordFingering = chordFingering;
        this.chordFrets = chordFrets;
        this.isLeftHanded = isLeftHanded;
    }

    /**
     * Given a chart, returns a new instance of it.
     * @param chart
     * @return
     */
    public static GuitarChart newInstance(GuitarChart chart) {
        return new GuitarChart(chart.getId(), chart.getChordName(), chart.getChordPosition(),
                chart.getChordFingering(), chart.getChordFrets(), chart.isLeftHanded());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChordName() {
        return chordName;
    }

    public void setChordName(String chordName) {
        this.chordName = chordName;
    }

    public String getChordPosition() {
        return chordPosition;
    }

    public void setChordPosition(String chordPosition) {
        this.chordPosition = chordPosition;
    }

    public String getChordFingering() {
        return chordFingering;
    }

    public void setChordFingering(String chordFingering) {
        this.chordFingering = chordFingering;
    }

    public String getChordFrets() {
        return chordFrets;
    }

    public void setChordFrets(String chordFrets) {
        this.chordFrets = chordFrets;
    }

    public boolean isLeftHanded() {
        return isLeftHanded;
    }

    public void setLeftHanded(boolean isLeftHanded) {
        this.isLeftHanded = isLeftHanded;
    }

}
