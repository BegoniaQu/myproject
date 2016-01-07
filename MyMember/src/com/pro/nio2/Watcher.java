package com.pro.nio2;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

public class Watcher {

	public static void main(String[] args) {
		Path this_dir = Paths.get("d:\\file");
		try {
			WatchService ws = this_dir.getFileSystem().newWatchService();
			this_dir.register(ws, StandardWatchEventKinds.ENTRY_CREATE,
					StandardWatchEventKinds.ENTRY_DELETE);

			while (true) {
				System.out.println("------------");
				WatchKey key = ws.take();
				List<WatchEvent<?>> list = key.pollEvents();
				for (WatchEvent<?> we : list) {
					Kind<?> kind = we.kind();
					// System.out.println(kind.name());
					System.out.println(we.context().toString());
				}
				key.reset(); // 为监控下一个通知做准备
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
