package baiTapTuan3;

import java.io.File;
import java.util.Optional;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class CommonOperations {
	private static void listMathodcals(File projectDir) {
		// TODO Auto-generated method stub
		
		new DirExplorner( 
				(level, path, file )-> {
					CheckClasses checkClasses = new CheckClasses();
					System.out.println(path);
					System.out.println("==============================");
					try {
						new VoidVisitorAdapter<Object>() {
							public void visit(ClassOrInterfaceDeclaration n, Object arg) {
								super.visit(n, arg);
								String clsName = n.getNameAsString();
								System.out.println(clsName);
								char c= clsName.charAt(0);
								
							
								
								if(!checkClasses.checkClassName(clsName)) {
									System.out.println("Error: class name "+clsName+" starts with a lowercase letter"
											+ " at line "+n.getBegin());
								}
                                    
                               Optional<Comment> ocmt = n.getComment();
								if (!checkClasses.checkComment(ocmt)) {
									System.out.println("Error: class "+clsName+" has no comment at line "+n.getBegin());
								}

							}
							
							@Override
							public void visit(PackageDeclaration n, Object arg) {
								super.visit(n, arg);
								System.out.println("package");
								
								
								System.out.println( n.getNameAsString());
								if (!CheckClasses.isValidPackageName(n.getNameAsString())) {
									System.out.println("Error: package name " + n.getNameAsString()
											+ " is not valid at line " + n.getBegin());
								}	

							}
							@Override
							public void visit(FieldDeclaration n, Object arg) {
								super.visit(n, arg);
								System.out.println("field");
								System.out.println( "L"+n.getBegin() +"]"+n);
								if (checkClasses.checkFieldNameAndMethod(n.getVariable(0).getNameAsString())) {
									System.out.println("Error: field name " + n.getVariable(0).getNameAsString()
											+ " starts with a lowercase letter at line " + n.getBegin());
								}
								
							}
							
							@Override
							public void visit(MethodDeclaration n, Object arg) {
								super.visit(n, arg);
								System.out.println("method");
								System.out.println( "L"+n.getBegin() +"]"+n.getDeclarationAsString());
								
								if (checkClasses.checkFieldNameAndMethod(n.getNameAsString())) {
									System.out.println("Error: method name " + n.getNameAsString()
											+ " starts with a lowercase letter at line " + n.getBegin());
								}

							}
							
						}.visit(StaticJavaParser.parse(file), null);
						System.out.println();
					} catch (Exception e) {
						// TODO: handle exception
						new RuntimeException(e);
						
					}
				},
				(level, path, file)-> path.endsWith(".java")
				).explore(projectDir);
	}
	
	public static void main(String[] args) {
		File projectDir = new File("E:\\KT_TKPM\\DoChiTuong_21129041 (1)\\DoChiTuong_21129041\\tuan3");
		listMathodcals(projectDir);
	}
}
