package com.insigma.afc.workbench.rmi;

import java.util.List;

import com.insigma.afc.topology.MetroNode;
import com.insigma.commons.application.IService;

public interface ICommandService extends IService {


	CmdHandlerResult command(int id, String userid,Long src,Object arg, List<MetroNode> targets);

	CmdHandlerResult command(int id, String userid,Long src, Object arg, MetroNode... targets);

	CmdHandlerResult command(int id, String userid,Long src,MetroNode... targets);

	CmdHandlerResult command(int id, String userid,Long src, List<MetroNode> targets);

	void alive();
}
