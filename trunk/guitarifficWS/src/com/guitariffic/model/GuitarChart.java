package com.guitariffic.model;

public class GuitarChart {
	private String id;
	private String name;
	private String position;
	private String fingering;
	private String frets;
	private boolean isLeftHanded;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @return the fingering
	 */
	public String getFingering() {
		return fingering;
	}

	/**
	 * @param fingering
	 *            the fingering to set
	 */
	public void setFingering(String fingering) {
		this.fingering = fingering;
	}

	/**
	 * @return the frets
	 */
	public String getFrets() {
		return frets;
	}

	/**
	 * @param frets
	 *            the frets to set
	 */
	public void setFrets(String frets) {
		this.frets = frets;
	}

	/**
	 * @return the isLeftHanded
	 */
	public boolean isLeftHanded() {
		return isLeftHanded;
	}

	/**
	 * @param isLeftHanded
	 *            the isLeftHanded to set
	 */
	public void setLeftHanded(boolean isLeftHanded) {
		this.isLeftHanded = isLeftHanded;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
