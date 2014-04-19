/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package com.guitariffic.model;

public class GuitarChart implements Cloneable {
	private String chordFingering;
	private String chordFrets;
	private String chordName;
	private String chordPosition;
	private String id;
	private boolean isLeftHanded;

	public GuitarChart() {
	}

	public GuitarChart(String id, String chordName, String chordPosition, String chordFingering, String chordFrets, boolean isLeftHanded) {
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
		return new GuitarChart(chart.getId(), chart.getChordName(), chart.getChordPosition(), chart.getChordFingering(), chart.getChordFrets(), chart.isLeftHanded());
	}

	public String getChordFingering() {
		return chordFingering;
	}

	public String getChordFrets() {
		return chordFrets;
	}

	public String getChordName() {
		return chordName;
	}

	public String getChordPosition() {
		return chordPosition;
	}

	public String getId() {
		return id;
	}

	public boolean isLeftHanded() {
		return isLeftHanded;
	}

	public void setChordFingering(String chordFingering) {
		this.chordFingering = chordFingering;
	}

	public void setChordFrets(String chordFrets) {
		this.chordFrets = chordFrets;
	}

	public void setChordName(String chordName) {
		this.chordName = chordName;
	}

	public void setChordPosition(String chordPosition) {
		this.chordPosition = chordPosition;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLeftHanded(boolean isLeftHanded) {
		this.isLeftHanded = isLeftHanded;
	}

	public static void print(GuitarChart chart) {
		System.out.println("Name: " + chart.getChordName());
		System.out.println("Fingering: " + chart.getChordFingering());
		System.out.println("Frets: " + chart.getChordFrets());
		System.out.println("Position: " + chart.getChordPosition());
		System.out.println("Left Handed: " + chart.isLeftHanded());
		System.out.println(" ");
	}

}
