public class DefaultPayrollProcessor {

    public void runPayroll() {
        SourceData sourceData = collectSourceData();
        ResultData resultData = calculatePaydata(sourceData);
        submitResult(resultData);
    }

    protected SourceData collectSourceData() {
        // ... default implementation
        return new SourceData();
    }

    protected ResultData calculatePaydata(SourceData sourceData) {
        // ... default implementation
        return new ResultData();
    }

    protected void submitResult(ResultData resultData) {
        // ... default implementation
    }
}
