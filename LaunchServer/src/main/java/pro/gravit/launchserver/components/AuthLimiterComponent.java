package pro.gravit.launchserver.components;

import pro.gravit.launcher.NeedGarbageCollection;
import pro.gravit.launchserver.LaunchServer;
import pro.gravit.launchserver.socket.Client;
import pro.gravit.launchserver.socket.response.auth.AuthResponse;
import pro.gravit.utils.HookException;

public class AuthLimiterComponent extends IPLimiter implements NeedGarbageCollection, AutoCloseable {
	private transient LaunchServer srv;
    @Override
    public void preInit(LaunchServer launchServer) {
    	srv = launchServer;
    }

    @Override
    public void init(LaunchServer launchServer) {
        launchServer.authHookManager.preHook.registerHook(this::preAuthHook);
    }

    @Override
    public void postInit(LaunchServer launchServer) {

    }

    public boolean preAuthHook(AuthResponse.AuthContext context, Client client) {
        if (!check(context.ip)) {
            throw new HookException(message);
        }
        return false;
    }
    public String message;

	@Override
	public void close() throws Exception {
        srv.authHookManager.preHook.unregisterHook(this::preAuthHook);
	}
}
