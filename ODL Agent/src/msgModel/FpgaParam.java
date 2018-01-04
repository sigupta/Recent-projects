package msgModel;

import java.util.BitSet;

public class FpgaParam {

 int inputPort = -1;
 int ethType = -1;
 String ipv4src = "No Value";
 String ipv4dst = "No value";
 int ipv4dst_Int = 0;
 int ipv4srcn = 0;
 int ipv4srcInt = 0;
 int ipv4dstn = 0;
 int ipv4dstInt = 0;
 int inWavelength = -1;
 int outWavelength = -1;
 BitSet tsbitmap ;
 int scheduleID = -1;
 int flowCounter = -1;
 int OutputPort = -1;
 int DropNoDropBit = 0;
 int InterIntraBit = 0;
 int drop_w = 0;
 int drop_ow = 0;
 int intra_w = 0;
 int intra_ow = 0;
 
 public int getdropW() {
	  return drop_w;
	 }
 
 public int getdropOW() {
	  return drop_ow;
	 }
 public int getintraW() {
	  return intra_w;
	 }
 public int getintraOW() {
	  return intra_ow;
	 }
public void setIntraW(int intra_w) {
	  this.intra_w = intra_w;
	  }
public void setIntraOW(int intra_ow) {
		this.intra_ow = intra_ow;
}
public void setDropW(int drop_w) {
	this.drop_w = drop_w ;
}
public void setDropOW(int drop_ow ) {
	this.drop_ow = drop_ow ;
}
 
 public int getInputPort() {
  return inputPort;
 }
 public void setInputPort(int inputPort) {
  this.inputPort = inputPort;
 }
 public int getEthType() {
  return ethType;
 }
 public void setEthType(int ethType) {
  this.ethType = ethType;
 }
 public String getIpv4src() {
  return ipv4src;
 }
 public void setIpv4src(String ipv4src) {
  this.ipv4src = ipv4src;
 }
 public int getIpv4srcInt() {
	  return ipv4srcInt;
 }
 public void setIpv4srcInt(int ipv4srcInt) {
	  this.ipv4srcInt = ipv4srcInt;
 }
 
 public int getIpv4dstInt() {
	  return ipv4dstInt;
}
public void setIpv4dstInt(int ipv4dstInt) {
	  this.ipv4dstInt = ipv4dstInt;
}
 
 public String getIpv4dst() {
  return ipv4dst;
 }
 public void setIpv4dst(String ipv4dst) {
  this.ipv4dst = ipv4dst;
 }
 public void setIpv4dst_Int(int ipv4dst_Int) {
	  this.ipv4dst_Int = ipv4dst_Int;
	 }
 public int getIpv4dst_Int() {
	  return ipv4dst_Int;
	 }
 public int getIpv4srcn() {
  return ipv4srcn;
 }
 public void setIpv4srcn(int ipv4srcn) {
  this.ipv4srcn = ipv4srcn;
 }
 public int getIpv4dstn() {
  return ipv4dstn;
 }
 public void setIpv4dstn(int ipv4dstn) {
  this.ipv4dstn = ipv4dstn;
 }
 public int getInWavelength() {
  return inWavelength;
 }
 public void setInWavelength(int inWavelength) {
  this.inWavelength = inWavelength;
 }
 public int getOutWavelength() {
  return outWavelength;
 }
 public void setOutWavelength(int outWavelength) {
  this.outWavelength = outWavelength;
 }
 public BitSet getTsbitmap() {
  return tsbitmap;
 }
 public void setTsbitmap(BitSet tsbitmap) {
  this.tsbitmap = tsbitmap;
 }
 public int getScheduleID() {
  return scheduleID;
 }
 public void setScheduleID(int scheduleID) {
  this.scheduleID = scheduleID;
 }
 public int getFlowCounter() {
  return flowCounter;
 }
 public void setFlowCounter(int flowCounter) {
  this.flowCounter = flowCounter;
 }
 public int getOutputPort() {
  return OutputPort;
 }
 public void setOutputPort(int outputPort) {
  OutputPort = outputPort;
 }
 public int getDropNoDropBit() {
	  return DropNoDropBit;
 }
 public void setDropNoDropBit(int dropNoDropBit) {
	this.DropNoDropBit = dropNoDropBit;
 }
 
 public int getInterIntraBit() {
	  return InterIntraBit;
 }
 public void setInterIntraBit(int interIntraBit) {
	this.InterIntraBit = interIntraBit;
 }
 
 
}