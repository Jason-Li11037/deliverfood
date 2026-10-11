import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<DefaultPayrollProcessor> processors = List.of(
                new DefaultPayrollProcessor(),
                new CalifironiaPayrollProcessor(),
                new TimeClockInputPayrollProcessor(),
                new PdfOutputPayrollProcessor());
        for (DefaultPayrollProcessor processor : processors) {
            processor.runPayroll();
        }
    }
}
