package net.minecraftforge.fml;

import pro.gravit.utils.helper.JVMHelper;

// FMLSecurityManager запрещает делать System.exit из классов
// Не входящих в пакеты самого Forge
public class SafeExitJVM {
    public static void exit(int code) {
        JVMHelper.RUNTIME.halt(code);
    }
}
