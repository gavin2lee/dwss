package com.gl.monitor.gather.service;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gl.monitor.gather.vo.ComputerInfo;

@Service
public class ComputerInfoGatherService {
	private static final Logger log = LoggerFactory.getLogger(ComputerInfoGatherService.class);

	public ComputerInfo gather() {
		ComputerInfo info = new ComputerInfo();
		
		int cpuCores = Runtime.getRuntime().availableProcessors();
		info.setCpuCores(cpuCores);
		
		long totalMemoryBytes = Runtime.getRuntime().totalMemory();
		//Kilo Mega Giga Tera Peta Exa Bronto
		long totalMemoryKilo = totalMemoryBytes / 1000;
		info.setTotalMemory(totalMemoryKilo);
		
		long freeMemoryBytes = Runtime.getRuntime().freeMemory();
		long freeMemoryKilo = freeMemoryBytes / 1000;
		long usedMemoryKilo = totalMemoryKilo - freeMemoryKilo;
		info.setUsedMemory(usedMemoryKilo); 
		
		File workDir = new File("./");
		long totalDiskSpaceBytes = workDir.getTotalSpace();
		long totalDiskSpaceMega = totalDiskSpaceBytes / 1000 / 1000;
		info.setTotalDisk(totalDiskSpaceMega);
		
		long freeDiskSpaceBytes = workDir.getFreeSpace();
		long freeDiskSpaceMega = freeDiskSpaceBytes / 1000 / 1000;
		long usedDiskSpaceMega = totalDiskSpaceMega - freeDiskSpaceMega;
		info.setUsedDisk(usedDiskSpaceMega);
		
		if(log.isDebugEnabled()){
			log.debug(info.toString());
		}
		return info;
	}
}
