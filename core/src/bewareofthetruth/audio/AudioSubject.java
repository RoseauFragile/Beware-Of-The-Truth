package bewareofthetruth.audio;

public interface AudioSubject {
    public void addObserver(AudioObserver audioObserver);
    public void removeObserver(AudioObserver audioObserver);
    public void removeAllObservers();
    //TODO passer d'event � string
    public void notify(final AudioObserver.AudioCommand command, AudioObserver.AudioTypeEvent event);
}