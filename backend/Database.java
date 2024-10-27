package backend;

import java.io.*;
import java.util.ArrayList; 


public class Database implements DatabaseInterface {

	// Saves user to file. If the user has no id, it will be assigned one.
	// If the user has an id/already exists in the file, it will be updated.
	public void saveUser(User user) {
		try {
			if (user.getId() == -1) {
				// Read first entry which is the next id
				FileInputStream fileIn = new FileInputStream(USERS_FILE);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				int nextId = in.readInt();
				in.close();

				// update the user
				user.setId(nextId);

				// write the incremented id back to the file
				FileOutputStream fileOut = new FileOutputStream(USERS_FILE);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeInt(nextId + 1);
				out.close();

				// append the user to the file
				fileOut = new FileOutputStream(USERS_FILE, true);
				out = new ObjectOutputStream(fileOut);
				out.writeObject(user);
				out.close();

				return;
			}

			// Have to check if the user already exists
			boolean userExists = this.getUser(user.getId()) != null;
			if (userExists) {
				// Update the user
				FileInputStream fileIn = new FileInputStream(USERS_FILE);
				ObjectInputStream in = new ObjectInputStream(fileIn);

				FileOutputStream fileOut = new FileOutputStream(USERS_FILE + ".tmp");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);

				User u;
				while (true) {
					try {
						u = (User) in.readObject();
						if (u.getId() == user.getId()) {
							u = user;
						}
						out.writeObject(u);
					} catch (EOFException e) {
						break;
					}
				}

				in.close();
				out.close();

				// Replace the old file with the new one
				File oldFile = new File(USERS_FILE);
				File newFile = new File(USERS_FILE + ".tmp");
				oldFile.delete();
				newFile.renameTo(oldFile);
				return;
			}

			// Append the user to the file
			FileOutputStream fileOut = new FileOutputStream(USERS_FILE, true);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(user);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public User getUser(int id) {
		try {
			FileInputStream fileIn = new FileInputStream(USERS_FILE);
			ObjectInputStream in = new ObjectInputStream(fileIn);

			User u;
			while (true) {
				try {
					u = (User) in.readObject();
					if (u.getId() == id) {
						in.close();
						return u;
					}
				} catch (EOFException e) {
					break;
				}
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public User getUser(String username) {
		try {
			FileInputStream fileIn = new FileInputStream(USERS_FILE);
			ObjectInputStream in = new ObjectInputStream(fileIn);

			User u;
			while (true) {
				try {
					u = (User) in.readObject();
					if (u.getUsername().equals(username)) {
						in.close();
						return u;
					}
				} catch (EOFException e) {
					break;
				}
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public User tryLogin(String username, String password) {
		try {
			FileInputStream fileIn = new FileInputStream(USERS_FILE);
			ObjectInputStream in = new ObjectInputStream(fileIn);

			User u;
			while (true) {
				try {
					u = (User) in.readObject();
					if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
						in.close();
						return u;
					}
				} catch (EOFException e) {
					break;
				}
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public void savePost(Post post) {
		try {
			if (post.getId() == -1) {
				// Read first entry which is the next id
				FileInputStream fileIn = new FileInputStream(POSTS_FILE);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				int nextId = in.readInt();
				in.close();

				// update the post
				post.setId(nextId);

				// write the incremented id back to the file
				FileOutputStream fileOut = new FileOutputStream(POSTS_FILE);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeInt(nextId + 1);
				out.close();

				// append the post to the file
				fileOut = new FileOutputStream(POSTS_FILE, true);
				out = new ObjectOutputStream(fileOut);
				out.writeObject(post);
				out.close();

				return;
			}

			// Have to check if the post already exists
			boolean postExists = this.getPost(post.getId()) != null;
			if (postExists) {
				// Update the post
				FileInputStream fileIn = new FileInputStream(POSTS_FILE);
				ObjectInputStream in = new ObjectInputStream(fileIn);

				FileOutputStream fileOut = new FileOutputStream(POSTS_FILE + ".tmp");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);

				Post p;
				while (true) {
					try {
						p = (Post) in.readObject();
						if (p.getId() == post.getId()) {
							p = post;
						}
						out.writeObject(p);
					} catch (EOFException e) {
						break;
					}
				}

				in.close();
				out.close();

				// Replace the old file with the new one
				File oldFile = new File(POSTS_FILE);
				File newFile = new File(POSTS_FILE + ".tmp");
				oldFile.delete();
				newFile.renameTo(oldFile);
				return;
			}

			// Append the post to the file
			FileOutputStream fileOut = new FileOutputStream(POSTS_FILE, true);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(post);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Post getPost(int id) {
		try {
			FileInputStream fileIn = new FileInputStream(POSTS_FILE);
			ObjectInputStream in = new ObjectInputStream(fileIn);

			Post p;
			while (true) {
				try {
					p = (Post) in.readObject();
					if (p.getId() == id) {
						in.close();
						return p;
					}
				} catch (EOFException e) {
					break;
				}
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Post> getPosts(int userId) {
		ArrayList<Post> posts = new ArrayList<Post>();
		try {
			FileInputStream fileIn = new FileInputStream(POSTS_FILE);
			ObjectInputStream in = new ObjectInputStream(fileIn);

			Post p;
			while (true) {
				try {
					p = (Post) in.readObject();
					if (p.getUserId() == userId) {
						posts.add(p);
					}
				} catch (EOFException e) {
					break;
				}
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return posts;
	}

	public ArrayList<Post> getFeed(int userId) {
		// TODO: will implement once friend system is implemented
		throw new UnsupportedOperationException("Unimplemented method 'getFeed'");
	}

	public void deletePost(int id) {
		try {
			FileInputStream fileIn = new FileInputStream(POSTS_FILE);
			ObjectInputStream in = new ObjectInputStream(fileIn);

			FileOutputStream fileOut = new FileOutputStream(POSTS_FILE + ".tmp");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);

			Post p;
			while (true) {
				try {
					p = (Post) in.readObject();
					if (p.getId() != id) {
						out.writeObject(p);
					}
				} catch (EOFException e) {
					break;
				}
			}

			in.close();
			out.close();

			// Replace the old file with the new one
			File oldFile = new File(POSTS_FILE);
			File newFile = new File(POSTS_FILE + ".tmp");
			oldFile.delete();
			newFile.renameTo(oldFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Also delete associated comments
		try {
			FileInputStream fileIn = new FileInputStream(COMMENTS_FILE);
			ObjectInputStream in = new ObjectInputStream(fileIn);

			FileOutputStream fileOut = new FileOutputStream(COMMENTS_FILE + ".tmp");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);

			Comment c;
			while (true) {
				try {
					c = (Comment) in.readObject();
					if (c.getPostId() != id) {
						out.writeObject(c);
					}
				} catch (EOFException e) {
					break;
				}
			}

			in.close();
			out.close();

			// Replace the old file with the new one
			File oldFile = new File(COMMENTS_FILE);
			File newFile = new File(COMMENTS_FILE + ".tmp");
			oldFile.delete();
			newFile.renameTo(oldFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Also delete associated post votes
		// TODO: will implement once post votes are implemented
	}

	public void saveComment(Comment comment) {
		try {
			if (comment.getId() == -1) {
				// Read first entry which is the next id
				FileInputStream fileIn = new FileInputStream(COMMENTS_FILE);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				int nextId = in.readInt();
				in.close();

				// update the comment
				comment.setId(nextId);

				// write the incremented id back to the file
				FileOutputStream fileOut = new FileOutputStream(COMMENTS_FILE);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeInt(nextId + 1);
				out.close();

				// append the comment to the file
				fileOut = new FileOutputStream(COMMENTS_FILE, true);
				out = new ObjectOutputStream(fileOut);
				out.writeObject(comment);
				out.close();

				return;
			}

			// Have to check if the comment already exists
			boolean commentExists = this.getComment(comment.getId()) != null;
			if (commentExists) {
				// Update the comment
				FileInputStream fileIn = new FileInputStream(COMMENTS_FILE);
				ObjectInputStream in = new ObjectInputStream(fileIn);

				FileOutputStream fileOut = new FileOutputStream(COMMENTS_FILE + ".tmp");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);

				Comment c;
				while (true) {
					try {
						c = (Comment) in.readObject();
						if (c.getId() == comment.getId()) {
							c = comment;
						}
						out.writeObject(c);
					} catch (EOFException e) {
						break;
					}
				}

				in.close();
				out.close();

				// Replace the old file with the new one
				File oldFile = new File(COMMENTS_FILE);
				File newFile = new File(COMMENTS_FILE + ".tmp");
				oldFile.delete();
				newFile.renameTo(oldFile);
				return;
			}

			// Append the comment to the file
			FileOutputStream fileOut = new FileOutputStream(COMMENTS_FILE, true);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(comment);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Comment getComment(int id) {
		try {
			FileInputStream fileIn = new FileInputStream(COMMENTS_FILE);
			ObjectInputStream in = new ObjectInputStream(fileIn);

			Comment c;
			while (true) {
				try {
					c = (Comment) in.readObject();
					if (c.getId() == id) {
						in.close();
						return c;
					}
				} catch (EOFException e) {
					break;
				}
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Comment> getComments(int postId) {
		ArrayList<Comment> comments = new ArrayList<Comment>();
		try {
			FileInputStream fileIn = new FileInputStream(COMMENTS_FILE);
			ObjectInputStream in = new ObjectInputStream(fileIn);

			Comment c;
			while (true) {
				try {
					c = (Comment) in.readObject();
					if (c.getPostId() == postId) {
						comments.add(c);
					}
				} catch (EOFException e) {
					break;
				}
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return comments;
	}

	public void deleteComment(int id) {
		try {
			FileInputStream fileIn = new FileInputStream(COMMENTS_FILE);
			ObjectInputStream in = new ObjectInputStream(fileIn);

			FileOutputStream fileOut = new FileOutputStream(COMMENTS_FILE + ".tmp");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);

			Comment c;
			while (true) {
				try {
					c = (Comment) in.readObject();
					if (c.getId() != id) {
						out.writeObject(c);
					}
				} catch (EOFException e) {
					break;
				}
			}

			in.close();
			out.close();

			// Replace the old file with the new one
			File oldFile = new File(COMMENTS_FILE);
			File newFile = new File(COMMENTS_FILE + ".tmp");
			oldFile.delete();
			newFile.renameTo(oldFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Also delete associated comment votes
		// TODO: will implement once comment votes are implemented
	}

	public void saveFriendship(Friendship friendship) {
		try {
			if (friendship.getId() == -1) {
				// Read first entry which is the next id
				FileInputStream fileIn = new FileInputStream(FRIENDSHIP_FILE);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				int nextId = in.readInt();
				in.close();

				// update the friendship
				friendship.setId(nextId);

				// write the incremented id back to the file
				FileOutputStream fileOut = new FileOutputStream(FRIENDSHIP_FILE);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeInt(nextId + 1);
				out.close();

				// append the friendship to the file
				fileOut = new FileOutputStream(FRIENDSHIP_FILE, true);
				out = new ObjectOutputStream(fileOut);
				out.writeObject(friendship);
				out.close();

				return;
			}

			// Have to check if the friendship already exists
			boolean friendshipExists = this.getFriendship(friendship.getId()) != null;
			if (friendshipExists) {
				// Update the friendship
				FileInputStream fileIn = new FileInputStream(FRIENDSHIP_FILE);
				ObjectInputStream in = new ObjectInputStream(fileIn);

				FileOutputStream fileOut = new FileOutputStream(FRIENDSHIP_FILE + ".tmp");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);

				Friendship f;
				while (true) {
					try {
						f = (Friendship) in.readObject();
						if (f.getId() == friendship.getId()) {
							f = friendship;
						}
						out.writeObject(f);
					} catch (EOFException e) {
						break;
					}
				}

				in.close();
				out.close();

				// Replace the old file with the new one
				File oldFile = new File(FRIENDSHIP_FILE);
				File newFile = new File(FRIENDSHIP_FILE + ".tmp");
				oldFile.delete();
				newFile.renameTo(oldFile);
				return;
			}

			// Append the friendship to the file
			FileOutputStream fileOut = new FileOutputStream(FRIENDSHIP_FILE, true);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(friendship);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Friendship getFriendship(int id) {
		try {
			FileInputStream fileIn = new FileInputStream(FRIENDSHIP_FILE);
			ObjectInputStream in = new ObjectInputStream(fileIn);

			Friendship f;
			while (true) {
				try {
					f = (Friendship) in.readObject();
					if (f.getId() == id) {
						in.close();
						return f;
					}
				} catch (EOFException e) {
					break;
				}
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<User> getFriends(int userId) {
		ArrayList<User> friends = new ArrayList<User>();
		try {
			FileInputStream fileIn = new FileInputStream(FRIENDSHIP_FILE);
			ObjectInputStream in = new ObjectInputStream(fileIn);

			Friendship f;
			while (true) {
				try {
					f = (Friendship) in.readObject();
					if (f.getUserId1() == userId) {
						friends.add(this.getUser(f.getUserId2()));
					} else if (f.getUserId2() == userId) {
						friends.add(this.getUser(f.getUserId1()));
					}
				} catch (EOFException e) {
					break;
				}
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return friends;
	}

	public void deleteFriendship(int id) {
		try {
			FileInputStream fileIn = new FileInputStream(FRIENDSHIP_FILE);
			ObjectInputStream in = new ObjectInputStream(fileIn);

			FileOutputStream fileOut = new FileOutputStream(FRIENDSHIP_FILE + ".tmp");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);

			Friendship f;
			while (true) {
				try {
					f = (Friendship) in.readObject();
					if (f.getId() != id) {
						out.writeObject(f);
					}
				} catch (EOFException e) {
					break;
				}
			}

			in.close();
			out.close();

			// Replace the old file with the new one
			File oldFile = new File(FRIENDSHIP_FILE);
			File newFile = new File(FRIENDSHIP_FILE + ".tmp");
			oldFile.delete();
			newFile.renameTo(oldFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveBlocked(Blocked blocked) {
		try {
			if (blocked.getId() == -1) {
				// Read first entry which is the next id
				FileInputStream fileIn = new FileInputStream(BLOCKED_FILE);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				int nextId = in.readInt();
				in.close();

				// update the blocked
				blocked.setId(nextId);

				// write the incremented id back to the file
				FileOutputStream fileOut = new FileOutputStream(BLOCKED_FILE);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeInt(nextId + 1);
				out.close();

				// append the blocked to the file
				fileOut = new FileOutputStream(BLOCKED_FILE, true);
				out = new ObjectOutputStream(fileOut);
				out.writeObject(blocked);
				out.close();

				return;
			}

			// Have to check if the blocked already exists
			boolean blockedExists = this.getBlocked(blocked.getId()) != null;
			if (blockedExists) {
				// Update the blocked
				FileInputStream fileIn = new FileInputStream(BLOCKED_FILE);
				ObjectInputStream in = new ObjectInputStream(fileIn);

				FileOutputStream fileOut = new FileOutputStream(BLOCKED_FILE + ".tmp");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);

				Blocked b;
				while (true) {
					try {
						b = (Blocked) in.readObject();
						if (b.getId() == blocked.getId()) {
							b = blocked;
						}
						out.writeObject(b);
					} catch (EOFException e) {
						break;
					}
				}

				in.close();
				out.close();

				// Replace the old file with the new one
				File oldFile = new File(BLOCKED_FILE);
				File newFile = new File(BLOCKED_FILE + ".tmp");
				oldFile.delete();
				newFile.renameTo(oldFile);
				return;
			}

			// Append the blocked to the file
			FileOutputStream fileOut = new FileOutputStream(BLOCKED_FILE, true);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(blocked);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Blocked getBlocked(int id) {
		try {
			FileInputStream fileIn = new FileInputStream(BLOCKED_FILE);
			ObjectInputStream in = new ObjectInputStream(fileIn);

			Blocked b;
			while (true) {
				try {
					b = (Blocked) in.readObject();
					if (b.getId() == id) {
						in.close();
						return b;
					}
				} catch (EOFException e) {
					break;
				}
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<User> getBlockedUsers(int userId) {
		ArrayList<User> blockedUsers = new ArrayList<User>();
		try {
			FileInputStream fileIn = new FileInputStream(BLOCKED_FILE);
			ObjectInputStream in = new ObjectInputStream(fileIn);

			Blocked b;
			while (true) {
				try {
					b = (Blocked) in.readObject();
					if (b.getBlockerId() == userId) {
						blockedUsers.add(this.getUser(b.getBlockedId()));
					}
				} catch (EOFException e) {
					break;
				}
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return blockedUsers;
	}

	public void deleteBlocked(int id) {
		try {
			FileInputStream fileIn = new FileInputStream(BLOCKED_FILE);
			ObjectInputStream in = new ObjectInputStream(fileIn);

			FileOutputStream fileOut = new FileOutputStream(BLOCKED_FILE + ".tmp");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);

			Blocked b;
			while (true) {
				try {
					b = (Blocked) in.readObject();
					if (b.getId() != id) {
						out.writeObject(b);
					}
				} catch (EOFException e) {
					break;
				}
			}

			in.close();
			out.close();

			// Replace the old file with the new one
			File oldFile = new File(BLOCKED_FILE);
			File newFile = new File(BLOCKED_FILE + ".tmp");
			oldFile.delete();
			newFile.renameTo(oldFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
