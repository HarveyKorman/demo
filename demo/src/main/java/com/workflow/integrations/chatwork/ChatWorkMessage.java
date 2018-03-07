package com.workflow.integrations.chatwork;

import com.google.gson.JsonObject;


public class ChatWorkMessage {

	private static final String CHANNEL = "channel";
	private static final String USERNAME = "username";
	private static final String HTTP = "http";
	private static final String ICON_URL = "icon_url";
	private static final String ICON_EMOJI = "icon_emoji";
	private static final String UNFURL_MEDIA = "unfurl_media";
	private static final String UNFURL_LINKS = "unfurl_links";
	private static final String TEXT = "text";
	private static final String ATTACHMENTS = "attachments";
	private static final String LINK_NAMES = "link_names";

//	private List<ChatWorkAttachment> attach = new ArrayList<ChatWorkAttachment>();
	private String channel = null;
	private String icon = null;
	private JsonObject chatWorkMessage = new JsonObject();

	private String text = null;
	private String username = null;

	private boolean unfurlMedia = false;
	private boolean unfurlLinks = false;

	private boolean linkNames = false;

	public ChatWorkMessage() {
	}

	public ChatWorkMessage(String text) {
		this(null, null, text);
	}

	public ChatWorkMessage(String username, String text) {
		this(null, username, text);
	}

	public ChatWorkMessage(String channel, String username, String text) {
		if (channel != null) {
			this.channel = channel;
		}

		if (username != null) {
			this.username = username;
		}

		this.text = text;
	}

//	public ChatWorkMessage addAttachments(ChatWorkAttachment attach) {
//		this.attach.add(attach);
//
//		return this;
//	}

	/**
	 * Convert SlackMessage to JSON
	 * 
	 * @return JsonObject
	 */
	public JsonObject prepare() {
		if (channel != null) {
			chatWorkMessage.addProperty(CHANNEL, channel);
		}

		if (username != null) {
			chatWorkMessage.addProperty(USERNAME, username);
		}

		if (icon != null) {
			if (icon.contains(HTTP)) {
				chatWorkMessage.addProperty(ICON_URL, icon);
			} else {
				chatWorkMessage.addProperty(ICON_EMOJI, icon);
			}
		}

		chatWorkMessage.addProperty(UNFURL_MEDIA, unfurlMedia);
		chatWorkMessage.addProperty(UNFURL_LINKS, unfurlLinks);
		chatWorkMessage.addProperty(LINK_NAMES, linkNames);

		if (text == null) {
			throw new IllegalArgumentException("Missing Text field @ SlackMessage");
		} else {
			chatWorkMessage.addProperty(TEXT, text);
		}

//		if (!attach.isEmpty()) {
//			chatWorkMessage.add(ATTACHMENTS, this.prepareAttach());
//		}

		return chatWorkMessage;
	}

//	private JsonArray prepareAttach() {
//		final JsonArray attachs = new JsonArray();
//		for (ChatWorkAttachment attach : this.attach) {
//			attachs.add(attach.toJson());
//		}
//
//		return attachs;
//	}

//	public ChatWorkMessage removeAttachment(int index) {
//		this.attach.remove(index);
//
//		return this;
//	}
//
//	public ChatWorkMessage setAttachments(List<ChatWorkAttachment> attach) {
//		this.attach = attach;
//
//		return this;
//	}

	public ChatWorkMessage setChannel(String channel) {
		if (channel != null) {
			this.channel = channel;
		}

		return this;
	}

	/**
	 * 
	 * @param icon
	 *            Avatar
	 * @return ChatWorkMessage
	 */
	public ChatWorkMessage setIcon(String icon) {
		if (icon != null) {
			this.icon = icon;
		}

		return this;
	}

	public ChatWorkMessage setText(String message) {
		if (message != null) {
			this.text = message;
		}

		return this;
	}

	public ChatWorkMessage setUsername(String username) {
		if (username != null) {
			this.username = username;
		}

		return this;
	}

	public ChatWorkMessage setUnfurlMedia(boolean unfurlMedia) {
		this.unfurlMedia = unfurlMedia;

		return this;
	}

	public ChatWorkMessage setUnfurlLinks(boolean unfurlLinks) {
		this.unfurlLinks = unfurlLinks;

		return this;
	}

	public ChatWorkMessage setLinkNames(boolean linkNames) {
		this.linkNames = linkNames;

		return this;
	}
}
