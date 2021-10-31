package com.nkuppan.giphybrowser.domain.network.model

import com.nkuppan.giphybrowser.domain.mapper.DomainMapper
import com.nkuppan.giphybrowser.domain.model.GiphyImage
import com.nkuppan.giphybrowser.domain.model.GiphyImageAttributes

class GiphyImageDtoMapper : DomainMapper<GiphyImageDto, GiphyImage> {

    override fun mapToDomainModel(model: GiphyImageDto): GiphyImage {

        val original = model.images.original
        val thumbnail = model.images.fixedHeight

        return GiphyImage(
            id = model.id,
            title = model.title,
            type = model.type,
            url = model.url,
            original = GiphyImageAttributes(
                height = original.height,
                width = original.width,
                url = original.url
            ),
            thumbnail = GiphyImageAttributes(
                height = thumbnail.height,
                width = thumbnail.width,
                url = thumbnail.url
            )
        )
    }

    override fun mapFromDomainModel(domainModel: GiphyImage): GiphyImageDto {
        TODO("Not needed for this cases")
    }

    fun toDomainList(initial: List<GiphyImageDto>): List<GiphyImage> {
        return initial.map { mapToDomainModel(it) }
    }
}
