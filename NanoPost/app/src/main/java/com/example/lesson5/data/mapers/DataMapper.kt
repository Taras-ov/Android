package com.example.lesson5.data.mapers

import com.example.lesson5.data.model.*
import com.example.lesson5.model.*

fun ApiProfile.toProfile(): Profile {
    return Profile(
        id = id,
        username = username,
        displayName = displayName,
        bio = bio,
        avatarId = avatarId,
        avatarLarge = avatarLarge,
        avatarSmall = avatarSmall,
        subscribed = subscribed,
        subscribersCount = subscribersCount,
        postsCount = postsCount,
        imagesCount = imagesCount,
        image = images.map {
            it.toImage()
        }
    )
}

fun ApiPost.toPost(): Post {
    return Post(
        id = id,
        owner = owner.toProfileCompact(),
        dateCreated = dateCreated,
        text = text,
        images = images.map {
            it.toImage()
        }
    )
}

fun ApiImage.toImage(): Image {
    return Image(
        id = id,
        owner = owner.toProfileCompact(),
        dateCreated = dateCreated,
        sizes = sizes.map {
            it.toImageSize()
        }
    )
}

fun ApiImageSize.toImageSize(): ImageSize {
    return ImageSize(
        width = width,
        height = height,
        url = url
    )
}

fun ApiProfileCompact.toProfileCompact(): ProfileCompact {
    return ProfileCompact(
        id = id,
        username = username,
        displayName = displayName,
        avatarUrl = avatarUrl,
        subscribed = subscribed
    )
}