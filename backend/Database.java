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

}
