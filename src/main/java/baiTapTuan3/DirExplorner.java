package baiTapTuan3;

import java.io.File;



public class DirExplorner {
	public interface FileHandler{
		void handle(int level, String path, File file);
	}
	public interface Filter {
		boolean interested(int level, String path, File file);
		
	}
	private FileHandler filehandler ;
	private Filter filter;
	
	public DirExplorner(FileHandler filehandler, Filter filter) {
		super();
		this.filehandler = filehandler;
		this.filter = filter;
	}
	
	public void explore(int level,String path, File file) {
		if(file.isDirectory()) {
			for (File child : file.listFiles()) {
				explore( +1, path +'/'+child.getName(), child);
				
			}
		}else {
			if(filter.interested(level, path, file)) {
				filehandler.handle(level, path, file);
			}
		}
	}
	
	public void explore(File root) {
		explore(0,"",root);
	}
	
	void test() {
		File projectDir = new File("T:\\DoChiTuong_21129041\\tuan3");
		DirExplorner.Filter filter = new Filter() {
			
			public boolean interested(int level, String path, File file) {
				return path.endsWith(".java");
			}
		};
		DirExplorner.FileHandler handler =new FileHandler() {
			
			public void handle(int level, String path, File file) {
				// TODO Auto-generated method stub
				System.out.println(path);
			}
		};
		DirExplorner di = new DirExplorner(handler, filter);
		di.explore(projectDir);
	}
	
}



























