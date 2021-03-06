package com.sherlock.imService.netty.codec;

import java.util.List;

import com.sherlock.imService.constant.MessageConstant;
import com.sherlock.imService.netty.configure.Configure;
import com.sherlock.imService.netty.entity.AbstractMessage;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class ImDecoder extends ByteToMessageDecoder{
//	private static Log log = LogFactory.getLog(ImDecoder.class);
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
//		if (!Configure.IS_BIG_EDIAN) {
//			in.order(ByteOrder.LITTLE_ENDIAN);
//		}
		if (in.readableBytes() < Configure.HEAD_LENGTH) {
			return;
		}
		AbstractMessage msg = TCPandUDPCommonDecode(in);
		if (msg != null) {
			out.add(msg);
		}
	}

	public static AbstractMessage TCPandUDPCommonDecode(ByteBuf in) {
		in.markReaderIndex();
		// 报文头部
		int startFlag = in.readInt();// 开始标志
		if (Configure.START_FLAG != startFlag) {
			throw new RuntimeException("消息开始标志不正确");
		}
		byte version = in.readByte();// 消息版本号
		if (Configure.MSG_VER != version) {
			throw new RuntimeException("消息版本号不正确");
		}
		byte msgType = in.readByte();// 消息类型
		int msgLen = in.readInt();
		// 分包处理
		if (in.writerIndex() < msgLen) {
			in.resetReaderIndex();
			return null;
		}
		String str = in.readCharSequence(msgLen - Configure.HEAD_LENGTH, Configure.CHARSET).toString();
		
		AbstractMessage msg = MessageConstant.getClientMessage(msgType, str);
		return msg;
	}
	
	
}
