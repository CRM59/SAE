package nextoo.validation.movies_app.data.dto;

public class WatchedDTO {
        private int pid;
        private int mid;

    public WatchedDTO(int pid, int mid) {
        this.pid = pid;
        this.mid = mid;
    }

    public int getPid() {
        return pid;
    }

    public int getMid() {
        return mid;
    }
}
