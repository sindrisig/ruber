package is.ru.honn.ruber.domain;

public class Trip
{
  protected int id;
  protected long requestTime;
  protected int productId;
  protected TripStatus status;
  protected double distance;
  protected long startTime;
  protected long endTime;
  protected String longitude;
  protected String latitude;
  protected String endLongitude;
  protected String endLatitude;

  public Trip()
  {

  }

  public Trip(int id, long requestTime, int productId, TripStatus status, double distance, long startTime, long endTime, String latitude, String longitude, String endLatitude, String endLongitude)
  {
    this.id = id;
    this.requestTime = requestTime;
    this.productId = productId;
    this.status = status;
    this.distance = distance;
    this.startTime = startTime;
    this.endTime = endTime;
    this.longitude = longitude;
    this.latitude = latitude;
    this.endLatitude = endLatitude;
    this.endLongitude = endLongitude;
  }

  public Trip(long requestTime, int productId, TripStatus status, double distance, long startTime, long endTime)
  {
    this.requestTime = requestTime;
    this.productId = productId;
    this.status = status;
    this.distance = distance;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public long getRequestTime()
  {
    return requestTime;
  }

  public void setRequestTime(long requestTime)
  {
    this.requestTime = requestTime;
  }

  public int getProductId()
  {
    return productId;
  }

  public void setProductId(int productId)
  {
    this.productId = productId;
  }

  public TripStatus getStatus()
  {
    return status;
  }

  public void setStatus(TripStatus status)
  {
    this.status = status;
  }

  public double getDistance()
  {
    return distance;
  }

  public void setDistance(double distance)
  {
    this.distance = distance;
  }

  public long getStartTime()
  {
    return startTime;
  }

  public void setStartTime(long startTime)
  {
    this.startTime = startTime;
  }

  public long getEndTime()
  {
    return endTime;
  }

  public void setEndTime(long endTime)
  {
    this.endTime = endTime;
  }

  public long getTime() { return this.endTime - this.startTime; }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getEndLongitude() {
        return endLongitude;
    }

    public void setEndLongitude(String endLongitude) {
        this.endLongitude = endLongitude;
    }

    public String getEndLatitude() {
        return endLatitude;
    }

    public void setEndLatitude(String endLatitude) {
        this.endLatitude = endLatitude;
    }

    @Override
  public String toString()
  {
    return "Trip{" +
        "id=" + id +
        ", requestTime=" + requestTime +
        ", productId=" + productId +
        ", status=" + status +
        ", distance=" + distance +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        '}';
  }
}
