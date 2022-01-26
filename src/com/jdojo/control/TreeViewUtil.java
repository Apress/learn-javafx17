// TreeViewUtil.java
package com.jdojo.control;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class TreeViewUtil {
	public static TreeView<String> getTreeView() {
		TreeItem<String> depts = new TreeItem<>("Departments");

		// Add items to depts
		TreeItem<String> isDept = new TreeItem<String>("IS");
		TreeItem<String> claimsDept = new TreeItem<String>("Claims");
		TreeItem<String> underwritingDept = new TreeItem<String>("Underwriting");
		depts.getChildren().addAll(isDept, claimsDept, underwritingDept);

		// Add employees for each dept
		isDept.getChildren().addAll(new TreeItem<String>("Doug Dyer"),
		                            new TreeItem<String>("Jim Beeson"),
		                            new TreeItem<String>("Simon Ng"));      

		claimsDept.getChildren().addAll(new TreeItem<String>("Lael Boyd"),
		                                new TreeItem<String>("Janet Biddle"));

		underwritingDept.getChildren().addAll(new TreeItem<String>("Ken McEwen"),
		                                      new TreeItem<String>("Ken Mann"),
		                                      new TreeItem<String>("Lola Ng"));

		// Craete a TreeView with depts as its root item
		TreeView<String> treeView = new TreeView<>(depts);
	
		return treeView;
	}
}
