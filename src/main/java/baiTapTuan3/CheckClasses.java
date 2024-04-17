package baiTapTuan3;

import java.util.Optional;

import com.github.javaparser.ast.comments.Comment;

public class CheckClasses {
	
	public boolean checkClassName(String className) {
		char c = className.charAt(0);
		if (c > 'a' && c < 'z') {
			
			return false;
		}
		return true;
	}
	
	public boolean checkComment( Optional<Comment> ocmt) {
		if (ocmt.isPresent()) {
			return true;
		}
		return false;
    }
	
	public static boolean isValidPackageName(String packageName) {
        // Biểu thức chính quy để kiểm tra
        String regex = "^com.companyname.\\..*$";


        return packageName.matches(regex);
    }
	
	//filed name phải bắt đầu bằng chữ thường
	public boolean checkFieldNameAndMethod(String fieldName) {
		char c = fieldName.charAt(0);
		if (c > 'a' && c < 'z') {
			return false;
		}
		return true;
	}
	

}
