/*******************************************************************************
 * Copyright (c) 2010 Oak Ridge National Laboratory.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.csstudio.opibuilder.actions;
import org.csstudio.opibuilder.runmode.OPIRunnerPerspective;
import org.csstudio.ui.util.perspective.PerspectiveHelper;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**The action that opens the OPI Runtime perspective
 * @author Xihui Chen
 */
public class OpenOPIRuntimePerspectiveAction implements IObjectActionDelegate {

	private IWorkbenchWindow window;

	@Override
    public void run(IAction action) {
		if(window == null)
			window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		PerspectiveHelper.showPerspectiveOrPromptForReset(OPIRunnerPerspective.ID, window);
	}

	@Override
    public void selectionChanged(IAction action, ISelection selection) {
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		window=targetPart.getSite().getWorkbenchWindow();
	}
}
