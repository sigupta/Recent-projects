/*******************************************************************************
 * This software is Copyright © 2012 The Regents of the University of 
 * California. All Rights Reserved.
 * 
 * Permission to copy, modify, and distribute this software and its 
 * documentation for educational, research and non-profit purposes, without fee, 
 * and without a written agreement is hereby granted, provided that the above 
 * copyright notice, this paragraph and the following three paragraphs appear in
 * all copies.
 * 
 * Permission to make commercial use of this software may be obtained by 
 * contacting:
 * Technology Transfer Office
 * 9500 Gilman Drive, Mail Code 0910
 * University of California
 * La Jolla, CA 92093-0910
 * (858) 534-5815
 * invent@ucsd.edu
 * 
 * This software program and documentation are copyrighted by The Regents of the
 * University of California. The software program and documentation are supplied
 * "as is", without any accompanying services from The Regents. The Regents does
 * not warrant that the operation of the program will be uninterrupted or error-
 * free. The end-user understands that the program was developed for research 
 * purposes and is advised not to rely exclusively on the program for any 
 * reason.
 * 
 * IN NO EVENT SHALL THE UNIVERSITY OF CALIFORNIA BE LIABLE TO
 * ANY PARTY FOR DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR
 * CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS, ARISING
 * OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION,
 * EVEN IF THE UNIVERSITY OF CALIFORNIA HAS BEEN ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE. THE UNIVERSITY OF
 * CALIFORNIA SPECIFICALLY DISCLAIMS ANY WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.
 * THE SOFTWARE PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, 
 * AND THE UNIVERSITY OF CALIFORNIA HAS NO OBLIGATIONS TO
 * PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR
 * MODIFICATIONS.
 */

/*
 * Filename: FpgaInfo.java
 * Version: 2.0
 * Description: Java API for RIFFA.
 * Author: Matthew Jacobsen
 * History: @mattj: Initial release. Version 2.0.
 */

/**
 * Value object to hold information about all the installed FPGA accessible by 
 * RIFFA.
 */
package edu.ucsd.cs.riffa;

public class FpgaInfo {
	private static final int NUM_FPGAS = 5;
	private int numFpgas;
	private int[] numChannels;
	private int[] id;
	private int[] vendorId;
	private int[] deviceId;
	private String[] name;

	/**
	 * Default constructor.
	 */
	public FpgaInfo() {
		this.numFpgas = 0;
		this.name = new String[NUM_FPGAS];
		this.id = new int[NUM_FPGAS];
		this.numChannels = new int[NUM_FPGAS];
		this.deviceId = new int[NUM_FPGAS];
		this.vendorId = new int[NUM_FPGAS];
	}
	
	/**
	 * Returns the number of RIFFA accessible FPGAs installed in the system.
	 *
	 * @returns Number of RIFFA accessible FPGAs installed in the system.
	 */
	public int getNumFpgas() {
		return this.numFpgas;
	}
	
	/**
	 * Sets the number of RIFFA accessible FPGAs installed in the system.
	 *
	 * @param val - Number of RIFFA accessible FPGAs installed in the system.
	 */
	public void setNumFpgas(int val) {
		this.numFpgas = val;		
	}

	/**
	 * Returns the number of RIFFA channels configured on the FPGA at position 
	 * pos.
	 *
	 * @returns Number of RIFFA channels configured on the FPGA at position pos.
	 */
	public int getNumChannels(int pos) {
		return this.numChannels[pos];
	}
	
	/**
	 * Sets the number of RIFFA channels configured on the FPGA at position pos.
	 *
	 * @param pos - Position of FPGA.
	 * @param val - Number of RIFFA channels configured on the FPGA at position 
	 * pos.
	 */
	public void setNumChannels(int pos, int val) {
		this.numChannels[pos] = val;		
	}

	/**
	 * Returns the FPGA id at position pos. This id is used to open the FPGA on 
	 * the Fpga's open method.
	 *
	 * @returns FPGA id at position pos..
	 */
	public int getId(int pos) {
		return this.id[pos];
	}
	
	/**
	 * Sets the FPGA id at position pos.
	 *
	 * @param pos - Position of FPGA.
	 * @param val - FPGA id at position pos.
	 */
	public void setId(int pos, int val) {
		this.id[pos] = val;		
	}

	/**
	 * Returns the name of the FPGA at position pos. This is typically the PCIe 
	 * bus and slot number.
	 *
	 * @returns Name of the FPGA at position pos.
	 */
	public String getName(int pos) {
		return this.name[pos];
	}
	
	/**
	 * Sets the name of the FPGA at position pos.
	 *
	 * @param pos - Position of FPGA.
	 * @param val - Name of the FPGA at position pos.
	 */
	public void setName(int pos, String val) {
		this.name[pos] = val;		
	}

	/**
	 * Returns the FPGA vendor id at position pos.
	 *
	 * @returns The FPGA vendor id at position pos.
	 */
	public int getVendorId(int pos) {
		return this.vendorId[pos];
	}
	
	/**
	 * Sets the FPGA vendor id at position pos.
	 *
	 * @param pos - Position of FPGA.
	 * @param val - The FPGA vendor id at position pos.
	 */
	public void setVendorId(int pos, int val) {
		this.vendorId[pos] = val;		
	}

	/**
	 * Returns the FPGA device id at position pos.
	 *
	 * @returns The FPGA device id at position pos.
	 */
	public int getDeviceId(int pos) {
		return this.deviceId[pos];
	}
	
	/**
	 * Sets the FPGA device id at position pos.
	 *
	 * @param pos - Position of FPGA.
	 * @param val - The FPGA device id at position pos.
	 */
	public void setDeviceId(int pos, int val) {
		this.deviceId[pos] = val;		
	}

	/**
	 * Returns a nicely formatted listing of all the RIFFA FPGAs detected.
	 * 
	 * @returns a nicely formatted String of all the RIFFA FPGAs detected.
	 */
	public String toString() {
		StringBuffer buffy = new StringBuffer();
		String eol = System.getProperty("line.separator");
		buffy.append("num fpgas: " + this.numFpgas + eol);
		for (int i=0; i < this.numFpgas; i++) {
			buffy.append("id: " + this.id[i] + eol);
			buffy.append("name: " + this.name[i] + eol);
			buffy.append("num channels: " + this.numChannels[i] + eol);
			buffy.append("vendor id: " + Integer.toHexString(this.vendorId[i]) + eol);
			buffy.append("device id: " + Integer.toHexString(this.deviceId[i]) + eol);
		}
		return buffy.toString();
	}
}
