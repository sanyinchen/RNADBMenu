/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */

import action.AdbAction;
import adb.AdbFacade;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by sanyinchen on 17/1/12.
 */
public class RNMenuAction extends AdbAction {

    @Override
    public void actionPerformed(AnActionEvent e, Project project) {

        AdbFacade.invokeDevmenu(project);

    }
}
