package cpw.mods.fml;
// FMLSecurityManager запрещает делать System.exit из классов
// Не входящих в пакеты самого Forge
public class SafeExitJVM {
    public static void exit(int code)
    {
        System.exit(code);
    }
}
