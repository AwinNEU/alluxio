/*
 * The Alluxio Open Foundation licenses this work under the Apache License, version 2.0
 * (the "License"). You may not use this work except in compliance with the License, which is
 * available at www.apache.org/licenses/LICENSE-2.0
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied, as more fully set forth in the License.
 *
 * See the NOTICE file distributed with this work for information regarding copyright ownership.
 */
package alluxio.network;

import io.netty.channel.epoll.Epoll;

import javax.annotation.concurrent.ThreadSafe;

/**
 * What type of netty channel to use. {@link #NIO} and {@link #EPOLL} are supported currently.
 */
@ThreadSafe
public enum ChannelType {
  NIO,
  /**
   * Use Linux's epoll for channel API. This type of channel only works on Linux.
   */
  EPOLL;

  /**
   * Determines the default type to use based off the system.
   * <p>
   * On Linux-based systems, {@link #EPOLL} is the default type for more consistent performance,
   * otherwise {@link #NIO}.
   * </p>
   *
   * @return a {@link ChannelType} compatible with the host
   */
  public static ChannelType defaultType() {
    if (Epoll.isAvailable()) {
      return ChannelType.EPOLL;
    } else {
      return ChannelType.NIO;
    }
  }
}
