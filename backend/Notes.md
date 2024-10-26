
## Overview

- User:
	- id
	- username
	- password
	- picture (base64 string)
	- [friends]
	- [blocked]
- Post:
	- id
	- [author]
	- picture (base64 string)
	- [upvotes]
	- [downvotes]
	- [comments]
- Comment:
	- id
	- author
	- [post]
	- content
	- [upvotes]
	- [downvotes]

## As tables

- User:
	- id: int
	- username: String
	- password: String
	- picture: String (base64)
- Post:
	- id: int
	- author: User.id
	- picture: String (base64)
- Comment:
	- id: int
	- author: User.id
	- post: Post.id
	- content: String
- Friendship:
	- user1: User.id
	- user2: User.id
- Block:
	- blocker: User.id
	- blocked: User.id
- PostVote:
	- user: User.id
	- post: Post.id
	- vote: int (1 for upvote, -1 for downvote)
- CommentVote:
	- user: User.id
	- comment: Comment.id
	- vote: int (1 for upvote, -1 for downvote)
