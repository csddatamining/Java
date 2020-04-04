import org.adoptopenjdk.jitwatch.model.IMetaMember;

public interface ICompilationChangeListener
{
	void compilationChanged(IMetaMember member);
}