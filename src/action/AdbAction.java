/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;

/**
 * Created by sanyinchen on 17/1/12.
 */
public abstract class AdbAction extends AnAction {
    @Override
    public final void actionPerformed(AnActionEvent e) {
        final Project project = e.getData(PlatformDataKeys.PROJECT);
        actionPerformed(e, project);
    }

    public abstract void actionPerformed(AnActionEvent e, Project project);
}
