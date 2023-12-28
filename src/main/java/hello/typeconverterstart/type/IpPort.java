package hello.typeconverterstart.type;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode //필드 값을 가지고 Equals, hashCode 구현한다. 필드의 값이 같다면 같은 객체 취급
@ToString
public class IpPort{
    private final String ip;
    private final int port;

    public IpPort(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
}
