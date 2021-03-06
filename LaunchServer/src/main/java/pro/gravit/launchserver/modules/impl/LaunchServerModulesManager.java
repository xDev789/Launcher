package pro.gravit.launchserver.modules.impl;

import java.nio.file.Path;
import java.util.Arrays;

import pro.gravit.launcher.modules.LauncherModule;
import pro.gravit.launcher.modules.LauncherModuleInfo;
import pro.gravit.launcher.modules.impl.SimpleModuleManager;
import pro.gravit.launchserver.LaunchServer;
import pro.gravit.utils.helper.LogHelper;

public class LaunchServerModulesManager extends SimpleModuleManager {
    public LaunchServerCoreModule coreModule;
    public LaunchServerModulesManager(Path modulesDir, Path configDir) {
        super(modulesDir, configDir);
        coreModule = new LaunchServerCoreModule();
        modules.add(coreModule);
    }
    public void fullInitializedLaunchServer(LaunchServer server)
    {
        initContext = new LaunchServerInitContext(server);
    }
    public void printModulesInfo()
    {
        for(LauncherModule module : modules)
        {
            LauncherModuleInfo info = module.getModuleInfo();
            LogHelper.info("[MODULE] %s v: %s p: %d deps: %s", info.name, info.version.getVersionString(), info.priority, Arrays.toString(info.dependencies));
        }
    }

    @Override
    public LauncherModule getCoreModule() {
        return coreModule;
    }
}
