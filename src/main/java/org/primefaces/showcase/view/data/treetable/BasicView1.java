/*
 * Copyright 2009-2014 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.showcase.view.data.treetable;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.ValueExpression;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.primefaces.component.column.Column;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.treetable.TreeTable;
import org.primefaces.model.TreeNode;
import org.primefaces.showcase.domain.Document;
import org.primefaces.showcase.service.DocumentService;

@ManagedBean(name = "ttBasicView1")
@ViewScoped
public class BasicView1 implements Serializable {

	private String testString = "";

	private TreeNode root;

	private Document selectedDocument;

	@ManagedProperty("#{documentService}")
	private DocumentService service;

	@PostConstruct
	public void init() {
		root = service.createDocuments();

		TreeTable table = new TreeTable();
		table.setId("treeTableId");
		table.setVar("document");
		table.setValueExpression("value", createValueExpression("#{ttBasicView1.root}", TreeNode.class));

		Column column = new Column();
		column.setId("col1");
		OutputLabel label = new OutputLabel();
		label.setId("ol1");
		label.setValueExpression("value", createValueExpression("#{document.name}", String.class));

		column.getChildren().add(label);

		table.getChildren().add(column);

		FacesContext context = FacesContext.getCurrentInstance();
		UIViewRoot viewRoot = context.getViewRoot();
		viewRoot.findComponent("form:mainPanel").getChildren().add(table);
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setService(DocumentService service) {
		this.service = service;
	}

	public Document getSelectedDocument() {
		return selectedDocument;
	}

	public void setSelectedDocument(Document selectedDocument) {
		this.selectedDocument = selectedDocument;
	}

	public static ValueExpression createValueExpression(String valueExpression, Class<?> valueType) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		return facesContext.getApplication().getExpressionFactory().createValueExpression(facesContext.getELContext(),
				valueExpression, valueType);
	}

	public String getTestString() {
		return testString;
	}

	public void setTestString(String testString) {
		this.testString = testString;
	}
	
	
	
}
